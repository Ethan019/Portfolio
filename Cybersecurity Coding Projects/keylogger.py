# You'll need to install pynput
# Security Permissions need to be adjusted on your computer.
import logging
from pynput.keyboard import Key, Listener

# ######### Generate log file (text file) or not ##########
SEND_LOGS_TO_FILE = True
LOG_FILE_NAME = 'key_log.txt'

logging.basicConfig( level=logging.INFO)

if SEND_LOGS_TO_FILE:
  logging.info(f'File used for logging: {LOG_FILE_NAME}')


# ######### Global Variables ##########
# This can be changed, but I'm not sure there's much value to tinkering
# with it. You can read more about why it exists in the key_up() function.

MODIFIER_KEYS = [
    Key.alt,
    Key.alt_r,
    Key.alt_l,
    Key.cmd,
    Key.cmd_r,
    Key.cmd_l,
    Key.ctrl,
    Key.ctrl_r,
    Key.ctrl_l,
    Key.shift,
    Key.shift_r,
    Key.shift_l,
]

IGNORED_KEYS = []

REMAP = {
    Key.alt_r: Key.alt,
    Key.alt_l: Key.alt,
    Key.ctrl_r: Key.ctrl,
    Key.ctrl_l: Key.ctrl,
    Key.cmd_r: Key.cmd,
    Key.cmd_l: Key.cmd,
    Key.shift_r: Key.shift,
    Key.shift_l: Key.shift,
}

keys_currently_down = []


# ######### Logging Functions ##########
def log(key):
  """
  We're looking to see what modifiers are pressed, in addition to the
  key that triggered the log request, and then want to log that.

  If <shift> alone is the modifier pressed down we don't really need to
  track it when used with a symbol. For example, if you press 'a',
  you'll see that 'a' is logged, and when you press 'shift+a', 'A'
  is logged (similarly '1' and '!'). It's not important that shift
  was used to get there, only the final key, since, when setting up
  your own keyboard, it's possible that you choose to put a symbol,
  like '@' on a layer that doesn't require shift. The "alone" part here
  may look a bit more complicated than needed; however, it's to account
  for the case that you don't remap <shift_r> to <shift> and you then
  either do <shift_r> + a = A, or even <shift_r> + <shift_l> + a = A.
  The code there maps <shift>, <shift_r>, and <shift_l> to <shift>, and
  then removes duplicates (multi-shift key-downs) using the set(...)
  constructor.

  If you use shift with a non-symbol (like the right arrow), then
  you'd want to log it as normal, since this behavior (which is
  expanding the selection one character to the right usually)
  wouldn't be distinguishable from simple <right>.

  Additionally, when there are modifiers in addition to shift (like
  <ctrl> or <cmd>), you'll see that <ctrl> + <shift> + 'a' is logged
  like that and not like <ctrl> + A. So maintaining the <shift> as
  part of the combo, is important to distinguish between <ctrl> + a
  and <ctrl> + <shift> + a (and logging <ctrl> + A seems,
  conceptually, to miss the mark on logging combos).
  """
  modifiers_down = [k for k in keys_currently_down if k in MODIFIER_KEYS]
  if list(set([
      Key.shift if k in [Key.shift, Key.shift_l, Key.shift_r] else k
      for k in modifiers_down
  ])) == [Key.shift] and key_is_a_symbol(key):
    modifiers_down = []
  log_entry = ' + '.join(
      sorted([key_to_str(k) for k in modifiers_down])
      + [key_to_str(key)]
  )
  logging.info(f'key: {log_entry}')

  if SEND_LOGS_TO_FILE:
    with open(LOG_FILE_NAME, 'a') as log_file:  # append mode
      log_file.write(f'{log_entry}\n')
      logging.debug(f'logged to file: {log_entry}')



# ######### Key Functions ##########
def key_is_a_symbol(key):
  return str(key)[0:4] != 'Key.'


def key_to_str(key):
  """
  The string representation pynput's Key.* objects isn't my preferred
  output, so this function standardizes how they're "stringified". The
  gist is that symbols (for example: a, b, Z, !, 3) are presented as-is,
  and other keys (for example: shift, control, command) are enclosed in
  brackets: '<' and '>'.

  There's a slightly more involved process for the symbols, only
  because the string representation includes the surrounding quotes of
  character (for example: "'a'") and it escapes backslashes, so that part
  undoes those two items.
  """
  s = str(key)
  if not key_is_a_symbol(key):
    s = f'<{s[4:]}>'
  else:
    s = s.encode('latin-1', 'backslashreplace').decode('unicode-escape')
    s = s[1:-1]  # trim the leading and trailing quotes
  return s


def key_down(key):
  """
  The goal here is to keep track of each key that's being pressed down
  and log when an action has taken place. By "action" I mean something
  that would be expected to send a keystroke to the computer (such as
  'a', as opposed to pressing just <shift>). If what's being pressed is
  only a modifier (the <cmd> in <cmd>-A), then we need to keep track
  that it's down, and wait until something else is pressed.

  First we only log the press if it's not already in the list, to avoid
  logging "sticky keys": pressing and holding a key and then seeing it
  typed many times. By exiting the function (stopping all processing)
  if it's in the keys_currently_down list (already being pressed), we
  ignore the repeats. This seems reasonable since in some places,
  holding the key will type the letter many times, and in others it
  will pop up a menu for selecting letters with diacritics. So it
  seems poorly defined as to what's actually happening on the screen
  anyways, during a hold-down. And the whole point of this program is
  to help you figure out what your fingers are doing, not necessarily
  what is going on in the computer.
  """
  if key in keys_currently_down:
    return

  keys_currently_down.append(key)
  logging.debug(
      f'key down : {key_to_str(key)} : '
      f'{[key_to_str(k) for k in keys_currently_down]}'
  )
  if key not in MODIFIER_KEYS:
    log(key)


def key_up(key):
  """
  The real action goes on when a key is pressed down, not up; however,
  this function needs to accomplish two key things: (1) registering that
  a key is no longer being pressed, which is especially important for
  the modifier keys, and (2) taking care of some routine clean up for
  keys that never get registered as having been released (up events,
  without a corresponding down event).

  It seems counter intuitive that a key could have been released, but
  never pressed; however, it seems to be the case. I don't know how
  general this case is, but it was consistent and repeatable for me.
  This seems to happen when you mix up the order of <shift> plus a
  symbol. For example, press down <shift>, then 'a', and what
  registers is a down-press of 'A' (which gets logged). If you then
  release the a-key, you're left with only <shift> being held down,
  which is an effectively "nothing" state. But instead, if you pick up
  <shift> first (while your finger is still holding down the a-key), the
  system registers <shift> up, and then 'a' (little a) down, but never
  'A' (big-A) up. And lastly when you pick up your finger from 'a', then
  the little-a gets cleared. In that sequence you'll see that big-A
  never got cleared (registered up). So now big-A is still in the
  keys_currently_down list, but you have no fingers on the keyboard.
  Another instance I've noticed is that as the system goes into and out
  of "secure input mode" (at least in macOS, the system locks down
  keyboard listening when you're in a password field), some key-up and
  key-down pairs don't match.

  In practice, this isn't actually that big of a deal because the
  logging event happens when a key is pressed down, and only the
  modifier keys (<ctrl>, <cmd>, etc.) plus the actual key that's being
  pressed, are being used to determine the combination. So a spurious
  'A' that's still in the keys_currently_down list, will have no effect
  when the 'w' is pressed in <cmd>-w (close a window).

  However, in the interest of good house-keeping, I like the idea of
  periodically clearing out these locked-in symbols from the
  keys_currently_down list. We accomplish this by waiting until two
  criteria have been met: (1) the length of the keys_currently_down list
  exceeds a threshold (5); so it doesn't
  happen too often or prematurely), and (2) there are no modifier keys
  being pressed (so we don't clean things up when you're in the middle
  of a key-combo).
  """
  global keys_currently_down

  try:
    keys_currently_down.remove(key)
  except ValueError:
    logging.warning(f'{key_to_str(key)} up event without a paired down event')
    if len(keys_currently_down) >= 5:
      logging.debug('key-down count is above locked-in limit')
      number_of_modifiers_down = len([
          k for k in keys_currently_down if k in MODIFIER_KEYS
      ])
      if number_of_modifiers_down == 0:
        logging.debug(
            'clearing locked-in keys-down: '
            f'{[key_to_str(k) for k in keys_currently_down]}'
        )
        keys_currently_down = []

  logging.debug(
      f'key up  : {key_to_str(key)} : '
      f'{[key_to_str(k) for k in keys_currently_down]}'
  )


def preprocess(key, f):
  """
  A simple wrapper to to do some preprocessing on the key press prior to
  sending it off for normal key-up/down handling.

  The remapping step helps simplify the logging. For example, I don't
  care to log whether the left or right Control key was used in a combo,
  just that Control was used. So the CTRL_R is remapped simply to CTRL.

  Ignoring comes after remapping so that the ignore list can take
  advantage of the remapping, for brevity. For example, you can
  ignore left and right shift, by remapping shift_r and shift_l to
  shift, and then ignoring shift.
  """
  k = key
  if key in REMAP:
    k = REMAP[key]
    logging.debug(f'remapped key {key_to_str(key)} -> {key_to_str(k)}')

  if k in IGNORED_KEYS:
    logging.debug(f'ignoring key: {key_to_str(k)}')
    return

  return f(k)

def main():
  with Listener(on_press=(lambda key: preprocess(key, key_down)),
                on_release=(lambda key: preprocess(key, key_up)),) as listener:
      listener.join()

if __name__ == '__main__':
  main()
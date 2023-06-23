import turtle

t=turtle.Turtle()
wn=turtle.Screen()
wn.setworldcoordinates(-300, -300, 300, 300)

f=open("mystery.txt", "r")
for aline in f:
    items = aline.split("\t")
    if items[0] =="UP":
        t.up()
    elif items[0]=="DOWN":
        t.down()
    else:
        t.goto(int(items[0]), int(items[1]))
f.close()
wn.exitonclick()

//******************************************************************************
	// NAME: Ethan Massingill
	// DATE: 10/10/2020
	// HOURS: 5 hours
	// CLASS: CSCI 200 Summer 2020
	// ASSSIGNMENT: Sphere.java for program-ch04
	// PURPOSE: class for TestSphere
	//******************************************************************************
	





public class Sphere{
	// place required comments here
	
		// define a double precision variable to hold the Sphere's getDiameter
		private double diameter, volume, surfaceArea;
		
		
		
		
		
		// define constructor to accept a double precision parameter and store it in the Sphere's diameter
		
		
		public Sphere(double diameter) {
			this.diameter=diameter;
			calcVolume(); 
			calcSurfaceArea();
		}

		// return the Sphere's diameter
		public double getDiameter(double diameter) {
			return this.diameter;
		}

		// set the Sphere's diameter with the parameter that is passed in
		public void setDiameter(double diameter) {
			this.diameter = diameter; // note the use of "this" which refers to the object itselt; must be used here to clarify the difference between the parameter called diameter and the instance variable called diameter
		}

		// return the volume per the formula given
		public double calcVolume() {
			double r= (double) diameter/2.0;
			volume =(4.0/3.0)*Math.PI*Math.pow(r, 3);
			volume =Math.round(volume *1000.0)/1000.0;
			return volume;
			
		}

		// return the surface area per the formula given
		public double calcSurfaceArea() {
			double r=(double) diameter/2.0;
			surfaceArea = 4*Math.PI*Math.pow(r, 2);
			surfaceArea =Math.round(surfaceArea *1000.0)/1000.0;
			 return surfaceArea ;
			 
			
		}
		
		// define toString that returns a string with the appropriate format
				public String toString() {
					return "A sphere with diameter "+ (diameter)+" has surface area "+surfaceArea+ " and volume "+volume+ ".";
				}

}

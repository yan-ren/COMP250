package assignment2;

import java.awt.Color;
import java.util.Random;
import java.util.Stack;

import org.w3c.dom.Node;

import assignment2.food.*;


public class Caterpillar {
	// All the fields have been declared public for testing purposes
	public Segment head;
	public Segment tail;
	public int length;
	public EvolutionStage stage;

	public Stack<Position> positionsPreviouslyOccupied;
	public int goal;
	public int turnsNeededToDigest;

	public static Random randNumGenerator = new Random(1);

	// Creates a Caterpillar with one Segment. It is up to students to decide how to implement this. 
	public Caterpillar(Position p, Color c, int goal) {
		this.goal = goal;
		this.head = new Segment(p, c);
		this.tail = this.head;
		this.stage = EvolutionStage.FEEDING_STAGE;
		this.positionsPreviouslyOccupied = new Stack<>();
		this.positionsPreviouslyOccupied.push(p);
		this.length = 1;
	}

	public EvolutionStage getEvolutionStage() {
		return this.stage;
	}

	public Position getHeadPosition() {
		return this.head.position;
	}

	public int getLength() {
		return this.length;
	}


	// returns the color of the segment in position p. Returns null if such segment does not exist
	public Color getSegmentColor(Position p) {
		// loop through each segment and check the position, if the position matches, return the color
		Segment current = head;
		while(current != null) {
			if(current.position.equals(p)) {
				return current.color;
			}
			current = current.next;
		}
		return null;
	}


	// shift all Segments to the previous Position while maintaining the old color
	public void move(Position p) {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */	
	}

	// a segment of the fruit's color is added at the end
	public void eat(Fruit f) {
		Segment newSegment = new Segment(positionsPreviouslyOccupied.pop(), f.getColor());
		
		tail.next = newSegment;
		tail = newSegment;
		this.length++;
	}

	// the caterpillar moves one step backwards because of sourness
	public void eat(Pickle p) {
		Segment current = this.head;
		while(current != null && current.next != null) {
			current.position = current.next.position;
		}

		this.tail = positionsPreviouslyOccupied.pop();
 	}


	// all the caterpillar's colors shuffles around
	public void eat(Lollipop lolly) {
		// add linked list segment into an array, so that the shuffle array is easier
		Segment[] segments = new Segment[this.length];
		Segment current = this.head;
		int index = 0;
		while(current != null) {
			segments[index] = current;
			index += 1;
			current = current.next;
		}

		for (int i = this.length - 1; i >= 1; i--){
			int j = randNumGenerator.nextInt(i+1);
			// swap color for segments at index i and j
			Color tmp = segments[j].color;
			segments[j].color = segments[i].color;
			segments[i].color = tmp;
		}
	}

	// brain freeze!!
	// It reverses and its (new) head turns blue
	public void eat(IceCream gelato) {
		// reverse single linked list
		Node pre = null;
		Node current = this.head;
		Node next = null;

		while(current != null) {
			next = current.next;
			current.next = pre;
			pre = current;
			current = next;
		}

		this.tail = this.head;
		this.head = pre;
		this.head.color = Color.BLUE;
		this.positionsPreviouslyOccupied.empty();
	}

	// the caterpillar embodies a slide of Swiss cheese loosing half of its segments. 
	public void eat(SwissCheese cheese) {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */	
	}


	public void eat(Cake cake) {
		/*
		 * TODO: ADD YOUR CODE HERE
		 */	
	}


	// This nested class was declared public for testing purposes
	public class Segment {
		private Position position;
		private Color color;
		private Segment next;

		public Segment(Position p, Color c) {
			this.position = p;
			this.color = c;
		}
	}


	public String toString() {
		Segment s = this.head;
		String gus = "";
		while (s!=null) {
			String coloredPosition = GameColors.colorToANSIColor(s.color) + 
					s.position.toString() + GameColors.colorToANSIColor(Color.WHITE);
			gus = coloredPosition + " " + gus;
			s = s.next;
		}
		return gus;
	}

}
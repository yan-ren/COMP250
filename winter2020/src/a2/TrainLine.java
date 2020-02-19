package a2;

import java.util.Arrays;
import java.util.Random;

public class TrainLine {

	private TrainStation leftTerminus;
	private TrainStation rightTerminus;
	private String lineName;
	private boolean goingRight;
	public TrainStation[] lineMap;
	public static Random rand;

	public TrainLine(TrainStation leftTerminus, TrainStation rightTerminus, String name, boolean goingRight) {
		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		this.lineMap = this.getLineArray();
	}

	public TrainLine(TrainStation[] stationList, String name, boolean goingRight)
	/*
	 * Constructor for TrainStation input: stationList - An array of TrainStation
	 * containing the stations to be placed in the line name - Name of the line
	 * goingRight - boolean indicating the direction of travel
	 */
	{
		TrainStation leftT = stationList[0];
		TrainStation rightT = stationList[stationList.length - 1];

		stationList[0].setRight(stationList[stationList.length - 1]);
		stationList[stationList.length - 1].setLeft(stationList[0]);

		this.leftTerminus = stationList[0];
		this.rightTerminus = stationList[stationList.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		for (int i = 1; i < stationList.length - 1; i++) {
			this.addStation(stationList[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public TrainLine(String[] stationNames, String name,
			boolean goingRight) {/*
									 * Constructor for TrainStation. input: stationNames - An array of String
									 * containing the name of the stations to be placed in the line name - Name of
									 * the line goingRight - boolean indicating the direction of travel
									 */
		TrainStation leftTerminus = new TrainStation(stationNames[0]);
		TrainStation rightTerminus = new TrainStation(stationNames[stationNames.length - 1]);

		leftTerminus.setRight(rightTerminus);
		rightTerminus.setLeft(leftTerminus);

		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;
		for (int i = 1; i < stationNames.length - 1; i++) {
			this.addStation(new TrainStation(stationNames[i]));
		}

		this.lineMap = this.getLineArray();

	}

	// adds a station at the last position before the right terminus
	public void addStation(TrainStation stationToAdd) {
		TrainStation rTer = this.rightTerminus;
		TrainStation beforeTer = rTer.getLeft();
		rTer.setLeft(stationToAdd);
		stationToAdd.setRight(rTer);
		beforeTer.setRight(stationToAdd);
		stationToAdd.setLeft(beforeTer);

		stationToAdd.setTrainLine(this);

		this.lineMap = this.getLineArray();
	}

	public String getName() {
		return this.lineName;
	}

	public int getSize() {
		int size = 1;
		TrainStation pointer = this.leftTerminus;
		while (!pointer.isRightTerminal()) {
			pointer = pointer.getRight();
			size += 1;
		}
		return size;
	}

	public void reverseDirection() {
		this.goingRight = !this.goingRight;
	}

	// You can modify the header to this method to handle an exception. You cannot
	// make any other change to the header.
	public TrainStation travelOneStation(TrainStation current, TrainStation previous) {
		// current station is transfer station and previous is not transfer, do transfer
		if(current.hasConnection && !previous.equals(current.getTransferStation())) {
			return current.getTransferStation();
		}
		// getNext gives the next station on the same line
		return getNext(current);
	}

	// You can modify the header to this method to handle an exception. You cannot
	// make any other change to the header.
	public TrainStation getNext(TrainStation station) {
		TrainStation target;
		try {
			target = findStation(station.getName());
		} catch (StationNotFoundException e) {
			throw e;
		}
		if (target.isRightTerminal() && goingRight) {
			return target.getLeft();
		} else if (target.isLeftTerminal() && !goingRight) {
			return target.getRight();
		} else if (goingRight) {
			return target.getRight();
		} else {
			return target.getLeft();
		}
	}

	// You can modify the header to this method to handle an exception. You cannot
	// make any other change to the header.
	public TrainStation findStation(String name) throws StationNotFoundException {
		TrainStation pointer = this.leftTerminus;
		TrainStation target = null;

		while (!pointer.isRightTerminal()) {
			if (pointer.getName().equals(name)) {
				target = pointer;
			}
			pointer = pointer.getRight();
		}
		// check the right terminus
		if (pointer.getName().equals(name)) {
			target = pointer;
		}

		if (target == null) {
			throw new StationNotFoundException("findStation method error: station " + name + " not found");
		} else {
			return target;
		}
	}

	public void sortLine() {
		//bubble sort doubly linked list
		int swapped, i;  
	    TrainStation pointer;  
	   
	    do
	    {  
	        swapped = 0;  
	        pointer = this.leftTerminus;  
	  
	        while (!pointer.getRight().isRightTerminal())  
	        {  
	        	//The result is a positive integer if this String object lexicographically follows the argument string.
	            if (pointer.getName().compareTo(pointer.getRight().getName()) > 0)  
	            {  
	            	if(pointer.isLeftTerminal()) {
	            		TrainStation next = pointer.getRight();
	            		pointer.setRight(pointer.getRight().getRight());
	            		pointer.setLeft(next);
	            		pointer.setNonTerminal();
	            		next.setLeft(null);
	            		next.setLeftTerminal();
	            		next.setRight(pointer);
	            		pointer.getRight().setLeft(pointer);
	            	}else {
	            		TrainStation next = pointer.getRight();
	            		TrainStation nextNext = pointer.getRight().getRight();
	            		TrainStation previous = pointer.getLeft();
	            		previous.setRight(next);
	            		pointer.setLeft(next);
	            		pointer.setRight(nextNext);
	            		next.setLeft(previous);
	            		next.setRight(pointer);
	            		nextNext.setLeft(pointer);
	            	}
	                swapped = 1;  
	            }  
	            pointer = pointer.getRight();  
	        }
	        
	        // swap with the right terminal if needed
	        if (pointer.getName().compareTo(pointer.getRight().getName()) > 0)  
            {  
            	// if doing swap here, pointer.right is right terminus
	        	pointer.getRight().setNonTerminal();
	        	pointer.getRight().setLeft(pointer.getLeft());
	        	pointer.getRight().setRight(pointer);
	        	pointer.getLeft().setRight(pointer.getRight());
	        	pointer.setLeft(pointer.getRight());
	        	pointer.setRight(null);
	        	pointer.isRightTerminal();
                swapped = 1;  
            }  
	        
	    }  
	    while (swapped != 0);  
	    
		this.lineMap = this.getLineArray();
	}

	public TrainStation[] getLineArray() {
		TrainStation[] trains = new TrainStation[getSize()];
		TrainStation current = this.leftTerminus;
		for(int i = 0; i < trains.length; i++) {
			trains[i] = current;
			current = current.getLeft();
		}
		return trains;
	}

	private TrainStation[] shuffleArray(TrainStation[] array) {
		Random rand = new Random();

		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			TrainStation temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		this.lineMap = array;
		return array;
	}

	public void shuffleLine() {

		// you are given a shuffled array of trainStations to start with
		TrainStation[] lineArray = this.getLineArray();
		TrainStation[] shuffledArray = shuffleArray(lineArray);

		TrainStation leftT = shuffledArray[0];
		TrainStation rightT = shuffledArray[shuffledArray.length - 1];

		shuffledArray[0].setRight(shuffledArray[shuffledArray.length - 1]);
		shuffledArray[shuffledArray.length - 1].setLeft(shuffledArray[0]);

		this.leftTerminus = shuffledArray[0];
		this.rightTerminus = shuffledArray[shuffledArray.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);

		for (int i = 1; i < shuffledArray.length - 1; i++) {
			this.addStation(shuffledArray[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public String toString() {
		TrainStation[] lineArr = this.getLineArray();
		String[] nameArr = new String[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			nameArr[i] = lineArr[i].getName();
		}
		return Arrays.deepToString(nameArr);
	}

	public boolean equals(TrainLine line2) {

		// check for equality of each station
		TrainStation current = this.leftTerminus;
		TrainStation curr2 = line2.leftTerminus;

		try {
			while (current != null) {
				if (!current.equals(curr2))
					return false;
				else {
					current = current.getRight();
					curr2 = curr2.getRight();
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TrainStation getLeftTerminus() {
		return this.leftTerminus;
	}

	public TrainStation getRightTerminus() {
		return this.rightTerminus;
	}
}

//Exception for when searching a line for a station and not finding any station of the right name.
class StationNotFoundException extends RuntimeException {
	String name;

	public StationNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "StationNotFoundException[" + name + "]";
	}
}

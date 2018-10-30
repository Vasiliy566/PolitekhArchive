import java.util.ArrayList;
import java.util.Scanner;

class NotePadNote { 
	Scanner scan = new Scanner(System.in);
	private String mainInfo;

	NotePadNote(String text) { // constructor
		mainInfo = text;
	}

	void noteEdit() { // shows note and swap it with user's input
		System.out.println(mainInfo);
		mainInfo = scan.nextLine();
	}
	String showInf() { // to get access to private field
		return mainInfo;
	}
}

class NotePad {
	Scanner scan = new Scanner(System.in);
	ArrayList<NotePadNote> notes = new ArrayList<NotePadNote>();

	void showNotes() { // show all notes after every method of this class
		for (int i = 0; i < notes.size(); i++) {
			System.out.print("note " + i + " : ");
			System.out.println(notes.get(i).showInf());
		}
	}

	void noteAdd() { // add note to place 'place' , if place is out of range - add to the end
		int place = scan.nextInt();
		if (place > notes.size()) 
		place = notes.size();
		NotePadNote inputNote = new NotePadNote(scan.nextLine());
		notes.add(place, inputNote);
		showNotes();
	}

	void noteDelete() {
		int place = scan.nextInt();
		notes.remove(place);
		showNotes();
	}
	void noteEdit() { // edit note number 'place' to note from console
		int place = scan.nextInt();
		notes.get(place).noteEdit();
		showNotes();
	}
}

public class Task_2 {

	public static void main(String[] args) {
		//some simple tests
		Scanner scan = new Scanner(System.in);
		NotePad notePad = new NotePad();
		notePad.noteAdd();
		notePad.noteAdd();
		notePad.noteAdd();
		notePad.noteDelete();
		notePad.noteAdd();
		notePad.noteEdit();
		notePad.showNotes();
		scan.close();
		
	}
}
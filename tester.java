package HW5;
//import TerminationFactory;


public class tester {
	public static void main(String[] args) {
		System.out.println("inside main!!!");
		TerminationFactory te = new TerminationFactory();
		
		AddTank(te,"dw");
		AddTank(te,"br");
		AddTank(te,"a");
		
		ListNonTerminated(te);
		DismantleTanks(te);
		
		ListNonTerminated(te); //2do - remove

		AddTank(te,"d");
		AddTank(te,"w");

		ListNonTerminated(te);		
		ListTerminated(te);
		
		SearchTank(te,"w");
		SearchTank(te,"dw");

		//DismantleTanks(te);
		
		ListNonTerminated(te);		
		ListTerminated(te);
		
		SearchTank(te,"dw");
		SearchTank(te,"bbb");
		for (int i = 0; i < 50; i++) {
			AddTank(te,String.valueOf((char)('a' + i)));
		}
		
		ListNonTerminated(te);		
		ListTerminated(te);
		
		DismantleTanks(te);
		
		ListNonTerminated(te);		
		ListTerminated(te);
		
	}
	static void DismantleTanks(TerminationFactory t) {
		t.dismantleTanks();
		System.out.println("Dismantled");
	}
	static void AddTank(TerminationFactory t, String s) {
		System.out.println("adding tank= " + s);
		Tank tnk = new Tank(s);
		t.insert(tnk);
		System.out.println("Added tank " + tnk.serialNumber + ": " + tnk.serialNumber());
	}
	static void ListNonTerminated(TerminationFactory t) {
		Tank[] arr = t.listNonTerminatedTanks();
		System.out.print("List non-terminated: ");
		PrintArr(arr);
	}
	static void ListTerminated(TerminationFactory t) {
		Tank[] arr = t.listTerminatedTanks();
		System.out.print("List terminated: ");
		PrintArr(arr);
	}
	static void PrintArr(Tank[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i].serialNumber + ":" + arr[i].serialNumber() + " ");
		}
		System.out.println();
	}
	static void SearchTank(TerminationFactory t, String s) {
		// 2do this function is WRONG!!
		Tank k = new Tank(s);
		switch(t.checkTerminated(k)) {
		case 0:
			System.out.println("Tank " + k.serialNumber + k.serialNumber() + " was terminated");
			break;
		case 1:
			System.out.println("Tank " + k.serialNumber + k.serialNumber() + " is awaiting termination");
			break;
		case 2:
			System.out.println("Tank " + k.serialNumber + k.serialNumber() + " is not here");
			break;
		}
	}
}
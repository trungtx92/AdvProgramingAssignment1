import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu implements MenuInterface{
	Scanner s;
	private Scanner sAdd;
	private Scanner sCheck;
	private Scanner sSearch;
	ArrayList<Profile> profileList = new ArrayList<>();
	
	public Menu() {
		dataProvide();
		int i = 0;
		do {
			s = new Scanner(System.in);
			System.out.println("1. list everyone.");
			System.out.println("2. select a person.");
			System.out.println("3. add a person");
			System.out.println("4. are these two direct friend?");
			System.out.println("5. find out relationship");
			System.out.println("6. exit");
			try {
				i = s.nextInt();
			}catch(InputMismatchException nm) {
				i = 0;
				System.out.println("wrong format!! please input a number!");
			}
			switch(i) {
			case 1:
				listOfProfile();
				break;
			case 2:
				selectProfile();
				break;
			case 3:
				addNewProfile();
				break;
			case 4:
				checkFriendRelation();
				break;
			case 5:
				findOutRelation();
				break;
				
			case 6:
				System.out.println("the program stopped!!");
				break;
			default:
				break;
			}
		}while(i != 6);
	}
	public void listOfProfile() {
		for(Profile pro: profileList) {
			System.out.println((profileList.indexOf(pro) + 1 ) +"	name:"+ pro.getName());
			System.out.println("	image:"+ pro.getImage());
			System.out.println("	status:"+ pro.getStatus());
			if(pro instanceof Childrend) {
				System.out.println("	age:"+ ((Childrend)pro).getAge());
				System.out.println("	this is a Children");
			}
			
			if(pro instanceof Adult) {
				System.out.println("	age:"+ ((Adult)pro).getAge());
				System.out.println("	This is an Adult");
			}
			System.out.println("------------------------");
		}
	}
	//---------------------------------
	public void selectProfile() {
		String searchName;
		s = new Scanner(System.in);
		System.out.println("input a name:");
		searchName = s.nextLine();
		if(checkExistanceProfile(searchName) == true) {
			subMenu(objProReturn(searchName));
		}
		else
			System.out.println("this person isn't existed!!!");
		
	}
	
	public void subMenu(Profile pro) {
		int i = 0;
		s = new Scanner(System.in);
		do {
			System.out.println("----------------------");
			System.out.println("1. Display information");
			System.out.println("2. Update information");
			System.out.println("3. Delete information");
			System.out.println("4. Connect");
			System.out.println("5. Exit");
			
			try {
				i = s.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Wrong format!! please input a number!!");
			}
			
			switch(i) {
				case 1:
					displayProfileInfo(pro);
					break;
				case 2:
					updaterProfileInfo(pro);
					break;
				case 3:
					deleteProfileInfo(pro);
					break;
				case 4:
					connectFriend(pro);
					break;
				case 5:
					break;
				default:
					break;
			}
		}while(i != 5);
	}
	
	public void displayProfileInfo(Profile pro) {

		if(pro instanceof Childrend) {
			((Childrend)pro).displayProfileInfo();
		}
		if(pro instanceof Adult) {
			((Adult)pro).displayProfileInfo();
		}
	}
	
	public void updaterProfileInfo(Profile pro) {
		String name;
		String image;
		String status;
		s = new Scanner(System.in);
		System.out.println("name:");
		name = s.nextLine();
		System.out.println("image:");
		image = s.nextLine();
		System.out.println("status:");
		status = s.nextLine();
		
		pro.updaterProfileInfo(name, image, status);
		
		}
	
	public void deleteProfileInfo(Profile pro) {
		profileList.remove(pro);
	}
	
	public void connectFriend(Profile pro) {
		String name;
		s = new Scanner(System.in);
		System.out.println("make friend with:");
		name = s.nextLine();
		if(checkExistanceProfile(name) == true ) {
			makeFriend(pro, objProReturn(name));
		}
		else
			System.out.println("The person you typed doesn't exist");
	}
	
	public void makeFriend(Profile pro1, Profile pro2) {
		
		if(pro1 instanceof Childrend && ((Childrend)pro1).getAge() <= 2) {
			System.out.println(pro1.getName()+ "'s age < 2 **** not enough to make friend");
		}
		if(pro2 instanceof Childrend && ((Childrend)pro2).getAge() <= 2) {
			System.out.println(pro2.getName()+ "'s age < 2 **** not enough to make friend");
		}
		
		
		
		if(pro1 instanceof Childrend && pro2 instanceof Childrend) {
			System.out.println("Gap:" + Math.abs(((Childrend)pro1).getAge() - ((Childrend)pro2).getAge()));
			if((Math.abs(((Childrend)pro1).getAge() - ((Childrend)pro2).getAge()) ) > 3) {
				System.out.println("The gap between ages greater than 3**** fail to make friend");
			}
			else 
				if(checkRelationship(pro1, pro2) == false && ((Childrend)pro1).getAge() > 2 && ((Childrend)pro2).getAge() > 2 && (Math.abs(((Childrend)pro1).getAge() - ((Childrend)pro2).getAge()) ) < 3 )  {
				pro1.getFriendList().add(pro2);
				pro2.getFriendList().add(pro1);
				}
		}
		if(pro1 instanceof Childrend && pro2 instanceof Adult) {
			if(checkRelationship(pro1, pro2) == false && ((Childrend)pro1).getAge() > 2 && checkPaternity((Adult)pro2, (Childrend)pro1) == false && (Math.abs(((Childrend)pro1).getAge() - ((Adult)pro2).getAge()) ) < 3) {
				pro1.getFriendList().add(pro2);
				pro2.getFriendList().add(pro1);
			}
			else
				System.out.println("Fail to make friend");
		}
		if(pro1 instanceof Adult && pro2 instanceof Childrend) {
			if(checkRelationship(pro1, pro2) == false && ((Childrend)pro2).getAge() > 2 && checkPaternity((Adult)pro1, (Childrend)pro2) == false && (Math.abs(((Adult)pro1).getAge() - ((Childrend)pro2).getAge()) ) < 3) {
				pro1.getFriendList().add(pro2);
				pro2.getFriendList().add(pro1);
			}
		}
		if(pro1 instanceof Adult && pro2 instanceof Adult) {
			if(checkRelationship(pro1, pro2) == false) {
				pro1.getFriendList().add(pro2);
				pro2.getFriendList().add(pro1);
			}
		}
		
	}
	//--------------------------------------------------------
	public void addNewProfile() {
		String name;
		String image;
		String status;
		int age = 0;
		String dadName;
		String momName;
		s = new Scanner(System.in);
		sAdd = new Scanner(System.in);
		boolean checkAge = false;;
		
		while(true) {
			System.out.println("input name:");
			name = sAdd.nextLine();
			if(checkExistanceProfile(name) == false) {
				break;
			}
			else
				System.out.println("this person is existed!!! Please type another name!!");
			
		}
		System.out.println("input image:");
		image = sAdd.nextLine();
		System.out.println("input status:");
		status = sAdd.nextLine();
		System.out.println("input age:");
		try {
			age = sAdd.nextInt();
			checkAge = true;
		}catch(InputMismatchException e) {
			checkAge = false;
			System.out.println("**** Fail to add****the age should be a number!!*****");
		}
		
		if(checkAge == true && age > 16) {
			profileList.add(new Adult(name, image, status, age));
			System.out.println("a new Adult is created!!!!");
		}
		
		if(checkAge == true && age < 16) {
			System.out.println("Dad:");
			dadName = s.nextLine();
			System.out.println("Mom:");
			momName = s.nextLine();
			
			if(checkExistanceProfile(dadName) == false || checkExistanceProfile(momName) == false || ((Adult)objProReturn(dadName)).getChild() != null || ((Adult)objProReturn(momName)).getChild() != null) {
				System.out.println("***create false*****Dad or mom does'nt exist or has another child!****");
			}
			else {
				profileList.add(new Childrend(name, image, status, age, (Adult)objProReturn(dadName), (Adult)objProReturn(momName)));
				((Adult)objProReturn(dadName)).setChild((Childrend)profileList.get(profileList.size()-1));
				((Adult)objProReturn(momName)).setChild((Childrend)profileList.get(profileList.size()-1));
				System.out.println("a new child is created!");
			}
		}
	}
	
	//---------------------------------------------------------
	public void checkFriendRelation() {

		String ps1;
		String ps2;
		sCheck = new Scanner(System.in);
		listOfProfile();
		System.out.println("input the first person's name:");
		ps1 = sCheck.nextLine();
		System.out.println("input the second person's name:");
		ps2 = sCheck.nextLine();
		
		if(checkExistanceProfile(ps1) == true && checkExistanceProfile(ps2) == true) {
			if(checkRelationship(objProReturn(ps1), objProReturn(ps2)) == true) {
				System.out.println("they are friends!");
			}
			else
				System.out.println("they aren't friends!");
		}
		else
			System.out.println("typed person doesn't exist!");
		
	}
	//---------------------------------------------------------
	public void findOutRelation() {
		String searchName = "";
		sSearch = new Scanner(System.in);
		listOfProfile();
		
		System.out.println("input a name:");
			searchName = sSearch.nextLine();
		for(Profile pro: profileList) {
			if(searchName.equals(pro.getName())) {
				showRelation(pro);
			}
			 
		}
	}
	public void showRelation(Profile pro) {
		if(pro instanceof Childrend) {
			((Childrend)pro).showRelation();
		}
		if(pro instanceof Adult) {
			((Adult)pro).showRelation();
		}
	}
	
	//---------------------------------------------------------
	public boolean checkRelationship(Profile pro1, Profile pro2) {
		boolean ckValidFriend1 = false;
		boolean ckValidFriend2 = false;
		for(Profile pro: pro1.getFriendList()) {
			if(pro.equals(pro2)) {
				ckValidFriend1 = true;
				break;
			}
		}
		for(Profile pro: pro2.getFriendList()) {
			if(pro.equals(pro1)) {
				ckValidFriend2 = true;
				break;
			}
		}
		if(ckValidFriend1 == true && ckValidFriend2 == true) 
			return true;
		else 
			return false;
	}
	
	public boolean checkExistanceProfile(String name) {
		boolean check = false;
		for(Profile pro: profileList) {
			if(name.equals(pro.getName())) {
				check = true;
				break;
			}
		}
		return check;
	}
	
	public Profile objProReturn(String name) {
		for(Profile pro: profileList) {
			if(name.equals(pro.getName())) {
				return pro;
			}
		}
		return null;
	}
	
	public boolean checkPaternity(Adult adult, Childrend child) {
		if(adult.equals(child.getDad()) || adult.equals(child.getMom())) {
			return true;
		}
		else 
			return false;
	}
	
	public void dataProvide() {
	
		Profile pro[] = new Profile[9];
		pro[0] = new Childrend("Alex", "a.jpg", "i am Alex", 15);
		pro[1] = new Adult("AlexDad", "a.jpg", "i am Alex dad", 40);
		pro[2] = new Adult("AlexMom", "a.jpg", "i am Alex mom", 34);
		pro[3] = new Childrend("Bill", "a.jpg", "i am Bill", 15);
		pro[4] = new Adult("BillDad", "a.jpg", "i am Bill dad", 45);
		pro[5] = new Adult("BillMom", "a.jpg", "i am Bill mom", 39);
		pro[6] = new Adult("Steven", "a.jpg", "i am Steven", 30);
		pro[7] = new Adult("Eliza", "a.jpg", "i am Eliza", 30);
		pro[8] = new Adult("Alice", "a.jpg", "i am Alice", 30);

		pro[0].getFriendList().add(pro[3]);
		pro[3].getFriendList().add(pro[0]);
		
		pro[2].getFriendList().add(pro[6]);
		pro[6].getFriendList().add(pro[2]);
		
		if(pro[0] instanceof Childrend && pro[1] instanceof Adult && pro[2] instanceof Adult){		
			((Childrend)pro[0]).setDad((Adult)pro[1]);
			((Childrend)pro[0]).setMom((Adult)pro[2]);
			((Adult)pro[1]).setChild((Childrend)pro[0]);
			((Adult)pro[2]).setChild((Childrend)pro[0]);
		}
		
		if(pro[3] instanceof Childrend && pro[4] instanceof Adult && pro[5] instanceof Adult){		
			((Childrend)pro[3]).setDad((Adult)pro[4]);
			((Childrend)pro[3]).setMom((Adult)pro[5]);
			((Adult)pro[4]).setChild((Childrend)pro[3]);
			((Adult)pro[5]).setChild((Childrend)pro[3]);
		}
		profileList.add(pro[0]);
		profileList.add(pro[1]);
		profileList.add(pro[2]);
		profileList.add(pro[3]);
		profileList.add(pro[4]);
		profileList.add(pro[5]);
		profileList.add(pro[6]);
		profileList.add(pro[7]);
		profileList.add(pro[8]);
		
	}
		
}





















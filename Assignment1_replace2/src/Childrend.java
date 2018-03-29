
public class Childrend extends Profile implements ChildrInterface {

	private Adult dad;
	private Adult mom;
	private int age;
	
	public Childrend(String name, String image, String status, int age) {
		super(name, image, status);
		this.setAge(age);
		// TODO Auto-generated constructor stub
	}
	
	public Childrend(String name, String image, String status, int age, Adult dad, Adult mom) {
		super(name, image, status);
		this.setAge(age);
		this.setDad(dad);
		this.setMom(mom);
		// TODO Auto-generated constructor stub
	}
	//------------------------------------
	public void displayProfileInfo() {
		System.out.println("name: "+ getName());
		System.out.println("image: "+ getImage());
		System.out.println("status: "+ getStatus());
		System.out.println("status: "+ getAge());
	}
	
	public void showRelation() {
		if(dad != null) {
			System.out.println(getName() + "'s dad: " + dad.getName());
		}
		if(mom != null) {
			System.out.println(getName() + "'s mom: " + mom.getName());
		}
	}
	
	//----------------------------
	public Adult getDad() {
		return dad;
	}

	public void setDad(Adult dad) {
		this.dad = dad;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Adult getMom() {
		return mom;
	}

	public void setMom(Adult mom) {
		this.mom = mom;
	}
	
}

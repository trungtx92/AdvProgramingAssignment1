
public class Adult extends Profile implements AdultInterface {

	private Childrend child;
	private int age;
	
	public Adult(String name, String image, String status, int age) {
		super(name, image, status);
		this.setAge(age);
		// TODO Auto-generated constructor stub
	}
	//---------------------------------
	public void displayProfileInfo() {
		System.out.println("name: "+ getName());
		System.out.println("image: "+ getImage());
		System.out.println("status: "+ getStatus());
		System.out.println("status: "+ getAge());
	}
	
	public void showRelation() {
		if(child != null) {
			System.out.println(getName() + "'s child: " + child.getName());
		}
		if(child.equals(null)) {
			System.out.println("This adult has no children");
		}
		
	}
	//---------------------------------
	public Childrend getChild() {
		return child;
	}

	public void setChild(Childrend child) {
		this.child = child;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

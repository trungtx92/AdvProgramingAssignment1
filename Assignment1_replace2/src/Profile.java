import java.util.ArrayList;

public abstract class Profile {
	private String name;
	private String image;
	private String status;
	private ArrayList<Profile> friendList = new ArrayList<>();
	
	public Profile(String name, String image, String status) {
		this.setName(name);
		this.setImage(image);
		this.setStatus(status);
	}

	public void displayProfileInfo() {
		System.out.println("name: "+ getName());
		System.out.println("image: "+ getImage());
		System.out.println("status: "+ getStatus());
	}
	
	public void updaterProfileInfo(String name, String image, String status) {
		
		setName(name);
		setImage(image);
		setStatus(status);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ArrayList<Profile> getFriendList() {
		return friendList;
	}

	public void setFriendList(ArrayList<Profile> friendList) {
		this.friendList = friendList;
	}
	
}

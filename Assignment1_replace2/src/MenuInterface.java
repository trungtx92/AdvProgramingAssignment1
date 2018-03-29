
public interface MenuInterface {
	
	public void listOfProfile();
	public void selectProfile();
	public void subMenu(Profile pro);
	public void displayProfileInfo(Profile pro);
	public void updaterProfileInfo(Profile pro);
	public void deleteProfileInfo(Profile pro);
	public void connectFriend(Profile pro);
	public void makeFriend(Profile pro1, Profile pro2);
	public void addNewProfile();
	public void checkFriendRelation();
	public void findOutRelation();
	public void showRelation(Profile pro);
	public boolean checkRelationship(Profile pro1, Profile pro2);
	public boolean checkExistanceProfile(String name);
	public Profile objProReturn(String name);
	public boolean checkPaternity(Adult adult, Childrend child);
	public void dataProvide();
}

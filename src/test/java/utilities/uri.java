package utilities;

public class uri extends base {

	/*
	 * base uri
	 */
	public String base_URL = prop("baseURL");
	/*
	 * paths for different calls.
	 */
	public String createUser = base_URL + prop("create.user.path");
	public String getUser = base_URL + prop("get.user.path");
	public String updateUser = base_URL + prop("update.user.path");
	public String loginUser = base_URL + prop("login.user.path");
	public String logoutUser = base_URL + prop("logout.user.path");
	public String deleteUser = base_URL + prop("delete.user.path");
	public String createcontact = base_URL + prop("create.contact.path");
	public String getcontact = base_URL + prop("get.contact.path");
	public String getcontactList = base_URL + prop("get.contactList.path");
	public String updatecontact = base_URL + prop("update.contact.path");
	public String updatecontactPatch = base_URL + prop("update.contactPatch.path");
	public String deletecontact = base_URL + prop("delete.contact.path");
}

package exseptions;

public class UserNotFound extends Exception{
  public UserNotFound(String userId) {
    super("TST_ERROR!!! User id = "+userId+" not found");
  }
}

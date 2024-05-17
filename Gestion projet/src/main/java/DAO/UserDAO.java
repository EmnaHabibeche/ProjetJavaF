package DAO;


import Entity.User;



public interface UserDAO {


     User getUserByEmail(String email) ;

      User login(String username, String password) ;




}

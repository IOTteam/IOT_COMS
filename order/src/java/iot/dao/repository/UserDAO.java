/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.User;
import java.util.List;

/**
 *
 * @author hatanococoro
 */
public interface UserDAO {
    
    public User getUser(String userId);
    public User getUserByname(String username,String password);
    public List<User> getAllUser();
    public void addUser(User user);
    public void editUser(User user);
    public void delUser(int loginId);
    
}

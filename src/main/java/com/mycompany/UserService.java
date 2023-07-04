package com.mycompany;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository repo;
  
  public List<User> listAll(){
    return (List<User>) repo.findAll();
  }

  public void save(User user) {
    repo.save(user);    
  }
  
  public User get(Integer id) throws UserNotFoundException {
    
    Optional<User> result = repo.findById(id);
    if(result.isPresent())
      return result.get();
    else
      throw new UserNotFoundException("Could not find any user with this id");
  }
  
  public void delete(Integer id) throws UserNotFoundException {
    
    Long count = repo.countById(id);
    if(count==null||count==0)
      throw new UserNotFoundException("Could not find any user with this id");
    else
      repo.deleteById(id);
  }
}

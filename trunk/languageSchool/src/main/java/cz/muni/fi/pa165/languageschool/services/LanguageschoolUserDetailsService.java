package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.api.DAO.TeacherDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
@Service
public class LanguageschoolUserDetailsService implements UserDetailsService{

	@Autowired
	private	StudentDAO studentDao;

	@Autowired
	private	TeacherDAO teacherDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		String password;
		HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		if("admin@admin.com".equals(email)) {
			password = "admin";
			authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
		} else {
			Teacher t = teacherDao.findTeacherByEmail(email);
			if(t != null){
				password = t.getPassword();
				authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
			}else{
				Student s = studentDao.findStudentByEmail(email);
				if(s == null) throw new UsernameNotFoundException("Username Not Found");
				password = s.getPassword();
				authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
			}
		}

		User user = new User(email, password, authorities);
		return user;
	}

}

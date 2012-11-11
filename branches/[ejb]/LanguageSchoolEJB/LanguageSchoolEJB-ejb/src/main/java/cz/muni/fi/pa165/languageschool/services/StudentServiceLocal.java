/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Student;

/**
 *
 * @author fivekeyem
 */
public interface StudentServiceLocal {
	void createStudent(Student student);
    
    void readStudent(Student student);
}

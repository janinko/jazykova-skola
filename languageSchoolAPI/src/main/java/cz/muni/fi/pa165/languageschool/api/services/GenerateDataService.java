package cz.muni.fi.pa165.languageschool.api.services;

public interface GenerateDataService {

    /*
     * Generate data and store them in DB
     *
     * @param courses number of courses to generate
     * @param lessons number of lessons to generate
     * @param students number of students to generate
     * @param teachers number of teachers to generate
     */ 
    void generateData(int courses, int lessons, int students, int techers);
}

import information.*;

public class Session {
    List<Student.Student> students = new ArrayList<>();

    public void addStudent(Student.Student student) {
        students.add(student);
    }

    public Map<String, Double> calculateAverageQuizScores() {
        Map<String, Double> averages = new HashMap<>();
        for (Student.Student student : students) {
            double average = student.quizScores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            averages.put(student.name, average);
        }
        return averages;
    }

    public void printQuizScoresInAscendingOrder() {
        Collections.sort(students, Comparator.comparingDouble(student -> student.quizScores.stream().mapToInt(Integer::intValue).sum()));
        for (Student.Student student : students) {
            System.out.println(student.name + ": " + student.quizScores);
        }
    }

    public void printPartTimeStudents() {
        List<String> partTimeStudents = new ArrayList<>();
        for (Student.Student student : students) {
            if (student instanceof Student.PartTimeStudent) {
                partTimeStudents.add(student.name);
            }
        }
        System.out.println("Part-Time Students: " + String.join(", ", partTimeStudents));
    }

    public void printFullTimeExamScores() {
        for (Student.Student student : students) {
            if (student instanceof Student.FullTimeStudent) {
                Student.FullTimeStudent fullTimeStudent = (Student.FullTimeStudent) student;
                System.out.println(fullTimeStudent.name + " Exam Scores: " + fullTimeStudent.examScores);
            }
        }
    }
}

package sc_d_lab7.onlineexaminationsystem;
import java.util.*;

class Exam {
    private final Map<Integer, String> examAnswers = new HashMap<>(); // Stores student ID and their answers
    private final int duration; // Exam duration in seconds

    public Exam(int duration) {
        this.duration = duration;
    }

    public synchronized void submitAnswer(int studentId, String answer) {
        examAnswers.put(studentId, answer);
        System.out.println("Student " + studentId + " submitted: " + answer);
    }

    public void showResults() {
        System.out.println("\nExam Results:");
        examAnswers.forEach((id, answer) -> System.out.println("Student " + id + ": " + answer));
    }

    public int getDuration() {
        return duration;
    }
}

class Student extends Thread {
    private final int studentId;
    private final Exam exam;

    public Student(int studentId, Exam exam) {
        this.studentId = studentId;
        this.exam = exam;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            int answerTime = random.nextInt(exam.getDuration() / 2) + 1; // Random answer time
            Thread.sleep(answerTime * 1000L); // Simulate thinking time
            String answer = "Answer from Student " + studentId;
            exam.submitAnswer(studentId, answer);
        } catch (InterruptedException e) {
            System.out.println("Student " + studentId + " was interrupted.");
        }
    }
}

class TimerThread extends Thread {
    private final int duration;

    public TimerThread(int duration) {
        this.duration = duration;
    }

    @Override
    public void run() {
        try {
            System.out.println("Exam started. Duration: " + duration + " seconds.");
            Thread.sleep(duration * 1000L);
            System.out.println("Time's up! Exam over.");
        } catch (InterruptedException e) {
            System.out.println("Timer was interrupted.");
        }
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        int examDuration = 20; // Duration in seconds
        int numberOfStudents = 5;

        Exam exam = new Exam(examDuration);

        // Start the timer thread
        TimerThread timer = new TimerThread(examDuration);
        timer.start();

        // Start student threads
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= numberOfStudents; i++) {
            Student student = new Student(i, exam);
            students.add(student);
            student.start();
        }

        // Wait for all threads to finish
        try {
            timer.join(); // Wait for timer to finish
            for (Student student : students) {
                student.join(); // Wait for each student thread
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Display results
        exam.showResults();
    }
}

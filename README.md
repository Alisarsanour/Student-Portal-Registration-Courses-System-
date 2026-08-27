# Student Portal & Course Registration System

A desktop application designed in **Java** to manage academic course registration, grade tracking, and prerequisite validation using custom data structures and GUI.

---

## 📖 User Guidelines & How to Use the App

The application displays a complete study panel containing **7 core courses**. Use the following controls and rules to navigate the system:

* **Add a Course:** 
  * **Left-click** on any eligible course in the *Complete Study Panel*. It will instantly be added to the *Student Choice Courses Panel*.
* **Remove a Course:** 
  * **Left-click** on the course you wish to remove from the *Student Choice Courses Panel*.
* **Undo Changes:** 
  * Click the **Undo** button to revert your most recent action.
* **Add Grades & Search:** 
  * **Right-click** on any course in the *Student Choice Courses Panel* to assign a grade, then use the **Search** button to view and update student records.
* **Check Course Unlocks:** 
  * **Right-click** on a course in the *Complete Study Panel* to view which future courses it unlocks.

---

## 🎨 Course Color Indicators

Courses in the study panel are dynamically color-coded based on prerequisites:

* 🔴 **Red:** Prerequisites are not yet completed; you cannot enroll in this course.
* ⚫ **Black:** All prerequisites are met; the course is available for registration.
* 🟢 **Green:** Course passed successfully.

---

## 🛠️ Tech Stack & Data Structures

* **Programming Language:** Java
* **User Interface:** Java Swing / AWT (JFrame)
* **Custom Data Structures:**
  * **Binary Search Tree (BST):** Efficient storage, searching, and grade retrieval.
  * **Linked List:** Dynamic tracking of registered courses.
  * **Stack:** Managing real-time **Undo** operations.
  * **OOP Architecture:** Modular object structure (`Course`, `StuP`, `BST`, `LinkedList`, `Stack`, `Main`).

---

## 💻 How to Run

1. Clone or download this repository.
2. Open the project files in your preferred IDE (e.g., IntelliJ IDEA).
3. Run `Main.java` to start the interface.

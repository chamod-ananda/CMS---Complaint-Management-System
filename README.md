Complaint Management System (CMS)


📌 Project Description
Complaint Management System (CMS) is a full-stack web-based application designed to streamline the process of handling employee complaints within an organization. The system offers a user-friendly interface where employees can submit, edit, and track their complaints, while admins can manage, update, and resolve them effectively.


🔧 Technologies Used

Java EE (Jakarta EE)
JSP & Servlets
JavaBeans
MySQL
Apache Tomcat
DBCP (Database Connection Pooling)
HTML, CSS

📁 Project Structure


CMS/

├── src/

│ ├── controller/ # Servlet classes for request handling

│ ├── dao/ # Data access objects (DAO)

│ ├── model/ # JavaBeans (POJOs)

│ └── util/ # Utility classes (e.g., DB connection)

├── webapp/

│ ├── jsp/

│ │ ├── admin/ # Admin-related JSP pages

│ │ └── employee/ # Employee-related JSP pages

│ └── css/ # Stylesheets

├── db/

│ └── schema.sql # MySQL database schema

└── README.md


🛠️ Setup And Configuration Guide


Clone the repository:

git clone https://github.com/chamod-ananda/CMS---Complaint-Management-System.git

Import the project into your IDE (e.g., IntelliJ IDEA or Eclipse).
Set up a MySQL database using the schema above.
Configure DBCPDataSource.java with your DB credentials.
Deploy the project on Apache Tomcat (v10+ recommended).
Access the system:
Employee Portal: http://localhost:8080/CMS_war_exploded/ComplaintServlet?action=dashboard

Admin Portal: http://localhost:8080/CMS_war_exploded/jsp/adminDashboard.jsp

📸 Screenshots


Employee Dashboard -

(https://github.com/user-attachments/assets/474f98fb-1fe7-4007-a21a-1c80e2b725b6)



Admin Dashboard -

(https://github.com/user-attachments/assets/d615bf8a-637a-4dd8-bd93-fb589d1c3fb0)



🙋 Author
Chamod Ananda
 📧 [chamodananda9@gmail.com]

💼 LinkedIn: [www.linkedin.com/in/chamod-ananda-37a881346]


Thank you for checking out the project! ⭐ If you find it useful, feel free to star the repo!

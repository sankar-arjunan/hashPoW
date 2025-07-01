# 🔐 HashPoW － Proof-of-Work Task Tracker

A full-stack task tracker where users submit text tasks, and the backend performs a **Proof-of-Work (PoW)** operation — finding a hash with **4 leading zeros** by brute-force nonce search. Track real-time task progress with a clean React UI.

---

## ⚙️ What it Does

✅ User Login (JWT-based, no passwords stored)  
✅ Add text-based tasks to the queue  
✅ Kafka-powered background worker processes tasks  
✅ PoW: Finds a hash of `input + nonce` starting with `0000`  
✅ Task states: QUEUED → PROCESSING → COMPLETED  
✅ Clean responsive React interface with task view & delete options  
✅ PostgreSQL stores user & task data  

---

## 🛠️ Tech Stack & Tools

- **Backend**: Spring Boot (Java 17)
- **Frontend**: React JS (Monospace styled UI)
- **Queue & Async Processing**: Apache Kafka
- **Database**: PostgreSQL
- **Auth**: JWT Token-based authentication
- **Containerization**: Docker-ready for both frontend & backend

---

## 📂 Key Features

- Users login securely via JWT (token stored in browser)  
- Submit tasks as plain text  
- Kafka ensures scalable, reliable task queuing  
- Backend performs intensive PoW search for hash starting with `0000`  
- Task list updates every 5 seconds  
- Delete or View task details on click  
- Simple, clean, mobile-friendly UI  

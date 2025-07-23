

# ✈️ Airport Baggage Tracking API

This Spring Boot project allows real-time tracking of airport baggage with **priority-based sorting**, **location scanning**, and **gate-based analytics**.

---

## 📦 Features

* ✅ Log bag scans with location, gate, timestamp, and priority (`HIGH`, `MEDIUM`, `LOW`)
* ✅ Query full history or latest scan for a bag
* ✅ Monitor active bags for a specific gate within a time window
* ✅ Automatically ranks bags by **priority + timestamp**
* ✅ View per-gate bag counts in a recent time window

---

## 🚀 Getting Started

### 🛠 Requirements

* Java 17+
* Maven 3.6+

### ▶️ Run the App

```bash
mvn spring-boot:run
```

Runs on: [http://localhost:8080](http://localhost:8080)

---

## ⚙️ API Endpoints

### 1️⃣ Log a Bag Scan

**POST** `/baggage/scan`

**Request Body**:

```json
{
  "bagTagId": "BG123",
  "destinationGate": "G5",
  "locationScanned": "Check-In Counter",
  "priorityLevel": "HIGH"
}
```

**Sample Response**:

```json
{
  "id": 1,
  "bagTagId": "BG123",
  "destinationGate": "G5",
  "locationScanned": "Check-In Counter",
  "priorityLevel": "HIGH",
  "scanTimestamp": "2025-07-23T12:34:56"
}
```

---

### 2️⃣ Get All or Latest Scans by Bag

**GET** `/baggage/scans/bag/{bagTagId}?latest=true|false`

* `latest=true`: Only most recent scan
* `latest=false`: Full scan history

---

### 3️⃣ Get Active Bags for a Gate (in recent N minutes)

**GET** `/baggage/active/gate/{gate}?since_minutes=60`

Returns active bags scanned at the gate within last `since_minutes`.

**Sorted by:**

* Priority → `HIGH → MEDIUM → LOW`
* Most recent scan timestamp

---

### 4️⃣ Get Scan Counts per Gate

**GET** `/baggage/stats/gate-counts?since_minutes=60`

**Example Response**:

```json
[
  { "gate": "G1", "count": 5 },
  { "gate": "G2", "count": 3 }
]
```

---

## 🧠 Priority Ranking Explained

When retrieving active bags:

* Priority levels:

  * `HIGH`: Urgent — loaded first
  * `MEDIUM`: Normal (default)
  * `LOW`: Least urgent

* Sorting used:

```sql
ORDER BY priorityLevel ASC, scanTimestamp DESC
```

---

## 🗂️ Project Structure

```
src/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
├── exception/
└── resources/
```

---

## 🧪 Test Scenarios

* ✅ Multiple scans per bag → all appear in history
* ✅ Bags scanned within last N mins → visible in active list
* ✅ Bags scanned > N mins ago → excluded from active list
* ✅ Gate with no bags → empty count

---

## 🧰 Technologies Used

* Java 17
* Spring Boot 3.2
* Spring Data JPA
* H2 In-Memory Database
* Maven

---


---

## 📈 Future Enhancements

* 📶 WebSocket stream for gate monitors
* ⏳ Historical trend charts
* ✈️ Flight schedule-aware prioritization
* 📤 CSV/Excel export of scans


import axios from "axios";

const API_BASE = "http://localhost:8080/api/seats";

export const getAllSeats = () => axios.get(API_BASE);

export const getAllEmployees = () => axios.get(`${API_BASE}/employees`);

export const assignSeat = (empId, floorSeatSeq) =>
  axios.post(`${API_BASE}/assign`, { empId, floorSeatSeq });

export const clearSeat = (floorSeatSeq) =>
  axios.post(`${API_BASE}/clear`, { floorSeatSeq });

axios.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error("API 錯誤:", error);
    alert("後端連線錯誤或伺服器無回應。");
    return Promise.reject(error);
  }
);

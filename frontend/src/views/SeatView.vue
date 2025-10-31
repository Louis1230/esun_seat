<template>
  <div class="container">
    <h2>員工座位安排系統</h2>

    <EmployeeSelector
      :employees="employees"
      @confirm="handleSelectEmployee"
    />

    <SeatGrid
      :seats="seats"
      :selectedSeat="selectedSeat"
      @selectSeat="handleSeatClick"
    />

    <div class="legend">
      <div class="legend-item"><span class="box empty"></span> 空位</div>
      <div class="legend-item"><span class="box occupied"></span> 已佔用</div>
      <div class="legend-item"><span class="box selected"></span> 請選擇</div>
    </div>

    <button class="submit-btn" @click="submitSeat">送出</button>
  </div>
</template>

<script>
import SeatGrid from '../components/SeatGrid.vue';
import EmployeeSelector from '../components/EmployeeSelector.vue';
import { getAllSeats, assignSeat, clearSeat } from '../api/seatApi';

export default {
  components: { SeatGrid, EmployeeSelector },
  data() {
    return {
      seats: [],
      employees: [],
      selectedSeat: null,
      selectedEmpId: null,
    };
  },
  methods: {
    async loadData() {
      const res = await getAllSeats();
      this.seats = res.data;
      this.employees = Array.from(
        new Map(
          this.seats
            .filter((s) => s.employee)
            .map((s) => [s.employee.empId, s.employee])
        ).values()
      );
    },
    handleSeatClick(seat) {
      this.selectedSeat = seat;
    },
    handleSelectEmployee(empId) {
      this.selectedEmpId = empId;
    },
    async submitSeat() {
      if (!this.selectedSeat) {
        alert('請先選擇座位');
        return;
      }

      if (this.selectedSeat.employee && !this.selectedEmpId) {
        await clearSeat(this.selectedSeat.floorSeatSeq);
        alert('座位已清空！');
      } else if (this.selectedEmpId) {
        await assignSeat(this.selectedEmpId, this.selectedSeat.floorSeatSeq);
        alert('座位指派成功！');
      } else {
        alert('請選擇員工或座位！');
      }

      this.selectedSeat = null;
      this.selectedEmpId = null;
      this.loadData();
    },
  },
  mounted() {
    this.loadData();
  },
};
</script>

<style scoped>
.container {
  text-align: center;
  padding: 20px;
}
.legend {
  display: flex;
  justify-content: center;
  gap: 15px;
  margin: 15px 0;
}
.legend-item {
  display: flex;
  align-items: center;
  gap: 5px;
}
.box {
  width: 20px;
  height: 20px;
  border-radius: 4px;
  display: inline-block;
}
.empty {
  background: #f0f0f0;
}
.occupied {
  background: #ff4d4f;
}
.selected {
  background: #90ee90;
}
.submit-btn {
  background: #004aad;
  color: white;
  padding: 8px 18px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: 0.2s;
}
.submit-btn:hover {
  background: #003a8c;
}
</style>

<template>
  <div class="seat-grid">
    <div
      v-for="(group, floor) in groupedSeats"
      :key="floor"
      class="floor-section"
    >
      <div class="seats">
        <div
          v-for="seat in group"
          :key="seat.floorSeatSeq"
          class="seat"
          :class="{
            occupied: seat.employee,
            selected: selectedSeat && selectedSeat.floorSeatSeq === seat.floorSeatSeq
          }"
          @click="$emit('selectSeat', seat)"
        >
          {{ seat.floorNo }}樓: 座位{{ seat.seatNo }}
          <div v-if="seat.employee" class="emp">
            [員編:{{ seat.employee.empId }}]
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["seats", "selectedSeat"],
  computed: {
    groupedSeats() {
      const groups = {};
      this.seats.forEach((seat) => {
        if (!groups[seat.floorNo]) groups[seat.floorNo] = [];
        groups[seat.floorNo].push(seat);
      });
      return groups;
    },
  },
};
</script>

<style scoped>
.floor-section {
  margin-bottom: 20px;
}

.seats {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px;
}

.seat {
  width: 160px;
  height: 70px;
  border-radius: 8px;
  background: #f3f3f3;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-weight: 600;
  transition: 0.2s;
}

.seat:hover {
  background: #dcecff;
}

.occupied {
  background: #ff4d4f;
  color: white;
}

.selected {
  background: #90ee90;
}

.emp {
  font-size: 13px;
  font-weight: 500;
}
</style>

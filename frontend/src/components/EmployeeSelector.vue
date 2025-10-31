<template>
  <div class="employee-selector">
    <label>選擇員工：</label>
    <select v-model="selectedEmpId" @change="confirmSelection">
      <option value="">請選擇員工</option>
      <option
        v-for="emp in employees"
        :key="emp.empId"
        :value="emp.empId"
        :class="{ selected: emp.empId === selectedEmpId }"
      >
        {{ emp.empId }} - {{ emp.name }}
      </option>
    </select>
  </div>
</template>

<script>
export default {
  props: {
    employees: Array,
  },
  data() {
    return {
      selectedEmpId: '',
    };
  },
  methods: {
    confirmSelection() {
      this.$emit('confirm', this.selectedEmpId);
    },
  },
};
</script>

<style scoped>
.employee-selector {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  font-weight: bold;
}

select {
  padding: 6px 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 14px;
  transition: all 0.2s ease-in-out;
}

select:focus {
  outline: none;
  border-color: #004aad;
  box-shadow: 0 0 4px #004aad;
}

option.selected {
  background-color: #007bff !important;
  color: white;
}
</style>

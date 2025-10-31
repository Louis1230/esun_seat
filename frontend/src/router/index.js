import { createRouter, createWebHistory } from 'vue-router';
import SeatView from '../views/SeatView.vue';

const routes = [
  { path: '/', name: 'SeatView', component: SeatView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

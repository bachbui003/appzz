import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminApiService, DashboardSummary, Department, User } from './core/admin-api.service';
import { TicketApiService } from './core/ticket-api.service';
import { Ticket, TicketStatus } from './core/ticket.model';

@Component({ selector: 'app-root', standalone: true, imports: [CommonModule, FormsModule], templateUrl: './app.component.html' })
export class AppComponent implements OnInit {
  summary?: DashboardSummary;
  tickets: Ticket[] = [];
  users: User[] = [];
  departments: Department[] = [];
  statuses: TicketStatus[] = ['NEW', 'ASSIGNED', 'IN_PROGRESS', 'RESOLVED', 'CLOSED', 'CANCELLED'];
  selectedStatus: Record<number, TicketStatus> = {};
  assigneeId: Record<number, number> = {};
  commentText: Record<number, string> = {};
  userForm: User = { fullName: '', email: '', passwordHash: 'change-me', role: 'USER', status: 'ACTIVE' };
  departmentForm: Department = { name: '', description: '' };

  constructor(private readonly adminApi: AdminApiService, private readonly ticketApi: TicketApiService) {}
  ngOnInit(): void { this.reload(); }
  reload(): void { this.adminApi.dashboard().subscribe(summary => this.summary = summary); this.ticketApi.listTickets().subscribe(tickets => this.tickets = tickets); this.adminApi.listUsers().subscribe(users => this.users = users); this.adminApi.listDepartments().subscribe(departments => this.departments = departments); }
  assign(ticket: Ticket): void { this.ticketApi.assignTicket(ticket.id, Number(this.assigneeId[ticket.id]), 1).subscribe(() => this.reload()); }
  changeStatus(ticket: Ticket): void { this.ticketApi.updateStatus(ticket.id, 1, this.selectedStatus[ticket.id] ?? ticket.status).subscribe(() => this.reload()); }
  comment(ticket: Ticket): void { this.ticketApi.addComment(ticket.id, 1, this.commentText[ticket.id]).subscribe(() => this.commentText[ticket.id] = ''); }
  createUser(): void { this.adminApi.createUser(this.userForm).subscribe(() => { this.userForm = { fullName: '', email: '', passwordHash: 'change-me', role: 'USER', status: 'ACTIVE' }; this.reload(); }); }
  createDepartment(): void { this.adminApi.createDepartment(this.departmentForm).subscribe(() => { this.departmentForm = { name: '', description: '' }; this.reload(); }); }
}

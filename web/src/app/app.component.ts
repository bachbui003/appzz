import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TicketApiService } from './core/ticket-api.service';
import { CreateTicketRequest, Ticket } from './core/ticket.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  tickets: Ticket[] = [];
  commentText: Record<number, string> = {};
  attachmentUrl: Record<number, string> = {};
  form: CreateTicketRequest = { title: '', description: '', category: 'General', priority: 'MEDIUM', requesterId: 1 };

  constructor(private readonly ticketsApi: TicketApiService) {}

  ngOnInit(): void { this.loadTickets(); }
  loadTickets(): void { this.ticketsApi.listTickets().subscribe(tickets => this.tickets = tickets); }
  createTicket(): void { this.ticketsApi.createTicket(this.form).subscribe(() => { this.form.title = ''; this.form.description = ''; this.loadTickets(); }); }
  addComment(ticket: Ticket): void { this.ticketsApi.addComment(ticket.id, 1, this.commentText[ticket.id]).subscribe(() => this.commentText[ticket.id] = ''); }
  upload(ticket: Ticket): void { this.ticketsApi.uploadAttachment(ticket.id, 1, 'issue-photo.jpg', this.attachmentUrl[ticket.id], 'image/jpeg').subscribe(() => this.attachmentUrl[ticket.id] = ''); }
}

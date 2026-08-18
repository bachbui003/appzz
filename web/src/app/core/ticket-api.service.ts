import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CreateTicketRequest, Ticket, TicketStatus } from './ticket.model';

@Injectable({ providedIn: 'root' })
export class TicketApiService {
  private readonly baseUrl = `${environment.apiUrl}/tickets`;

  constructor(private readonly http: HttpClient) {}

  listTickets(): Observable<Ticket[]> { return this.http.get<Ticket[]>(this.baseUrl); }
  createTicket(payload: CreateTicketRequest): Observable<Ticket> { return this.http.post<Ticket>(this.baseUrl, payload); }
  addComment(ticketId: number, authorId: number, content: string): Observable<unknown> {
    return this.http.post(`${this.baseUrl}/${ticketId}/comments`, { authorId, content });
  }
  uploadAttachment(ticketId: number, uploadedById: number, fileName: string, fileUrl: string, contentType?: string): Observable<unknown> {
    return this.http.post(`${this.baseUrl}/${ticketId}/attachments`, { uploadedById, fileName, fileUrl, contentType });
  }
  assignTicket(ticketId: number, assigneeId: number, changedById: number): Observable<Ticket> {
    return this.http.patch<Ticket>(`${this.baseUrl}/${ticketId}/assign`, { assigneeId, changedById });
  }
  updateStatus(ticketId: number, changedById: number, status: TicketStatus): Observable<Ticket> {
    return this.http.patch<Ticket>(`${this.baseUrl}/${ticketId}/status`, { changedById, status });
  }
}

export type TicketStatus = 'NEW' | 'ASSIGNED' | 'IN_PROGRESS' | 'RESOLVED' | 'CLOSED' | 'CANCELLED';
export type TicketPriority = 'LOW' | 'MEDIUM' | 'HIGH' | 'URGENT';

export interface Ticket {
  id: number;
  title: string;
  description: string;
  status: TicketStatus;
  priority: TicketPriority;
  category: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface CreateTicketRequest {
  title: string;
  description: string;
  category: string;
  priority: TicketPriority;
  requesterId: number;
  departmentId?: number;
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface DashboardSummary { totalTickets: number; new: number; assigned: number; in_progress: number; resolved: number; closed: number; cancelled: number; }
export interface User { id?: number; fullName: string; email: string; passwordHash: string; role: 'USER' | 'IT_STAFF' | 'ADMIN'; status: 'ACTIVE' | 'INACTIVE'; }
export interface Department { id?: number; name: string; description?: string; }

@Injectable({ providedIn: 'root' })
export class AdminApiService {
  constructor(private readonly http: HttpClient) {}
  dashboard(): Observable<DashboardSummary> { return this.http.get<DashboardSummary>(`${environment.apiUrl}/dashboard/summary`); }
  listUsers(): Observable<User[]> { return this.http.get<User[]>(`${environment.apiUrl}/users`); }
  createUser(user: User): Observable<User> { return this.http.post<User>(`${environment.apiUrl}/users`, user); }
  listDepartments(): Observable<Department[]> { return this.http.get<Department[]>(`${environment.apiUrl}/departments`); }
  createDepartment(department: Department): Observable<Department> { return this.http.post<Department>(`${environment.apiUrl}/departments`, department); }
}

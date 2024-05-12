import { Component } from '@angular/core';
import { PageableComponent } from '../../util/PageableComponent';
import { MatDialog } from '@angular/material/dialog';
import { TestDialogComponent } from './test-dialog/test-dialog.component';
import { DeleteDialogComponent } from '../../components/delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-test-table',
  templateUrl: './test-table.component.html',
  styleUrl: './test-table.component.scss',
})
export class TestTableComponent extends PageableComponent {
  tests = [
    { name: 'Test 1', age: 20 },
    { name: 'Test 2', age: 30 },
    { name: 'Test 3', age: 40 },
  ];
  constructor(public dialog: MatDialog) {
    super(1, 5, ['name', 'min_age', 'max_age'], ['', '', '']);
  }

  openEditDialog(index: number): void {
    const dialogRef = this.dialog.open(TestDialogComponent, {
      data: this.tests[index],
    });
    dialogRef.afterClosed().subscribe((editedJournalCode) => {
      if (editedJournalCode) {
        this.tests[index] = editedJournalCode;
      }
    });
  }

  openDeleteDialog(index: number): void {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      data: this.tests[index].name,
    });
    dialogRef.afterClosed().subscribe((name) => {
      if (name) {
        this.tests = this.tests.filter((_, i) => i != index);
      }
    });
  }
}

import { Component, OnInit, Inject } from '@angular/core';
import { ApprovalRuleService } from '../approval-rule.service';
import { ApprovalRule } from '../model/approval-rule.model';
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { switchMap } from 'rxjs/operators';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  displayedColumns: string[] = ['Id', 'matterCode', 'season', 'year', 'options'];
  approvalRules: ApprovalRule[];

  constructor(private approvalRuleService: ApprovalRuleService,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.getApprovalRules();
  }

  edit(id) {
    this.router.navigate(['update/' + id]);
  }

  delete(id) {
    this.approvalRuleService.delete(id).subscribe((res) => {
        window.location.reload();
    });
  }

  openDialog(id): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      data: {id: id}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.delete(result.id);
    });
  }


  getApprovalRules() {
    this.approvalRuleService.all().subscribe((res) => {
      this.approvalRules = res;
    });
  }


}

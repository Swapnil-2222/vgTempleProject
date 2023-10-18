import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IDelegate } from '../delegate.model';
import { DelegateService } from '../service/delegate.service';

@Component({
  standalone: true,
  templateUrl: './delegate-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DelegateDeleteDialogComponent {
  delegate?: IDelegate;

  constructor(
    protected delegateService: DelegateService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.delegateService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}

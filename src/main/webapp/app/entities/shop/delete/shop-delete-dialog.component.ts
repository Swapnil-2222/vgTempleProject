import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IShop } from '../shop.model';
import { ShopService } from '../service/shop.service';

@Component({
  standalone: true,
  templateUrl: './shop-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ShopDeleteDialogComponent {
  shop?: IShop;

  constructor(
    protected shopService: ShopService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shopService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}

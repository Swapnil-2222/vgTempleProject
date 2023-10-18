import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IShop } from '../shop.model';
import { ShopService } from '../service/shop.service';
import { ShopFormService, ShopFormGroup } from './shop-form.service';

@Component({
  standalone: true,
  selector: 'jhi-shop-update',
  templateUrl: './shop-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ShopUpdateComponent implements OnInit {
  isSaving = false;
  shop: IShop | null = null;

  editForm: ShopFormGroup = this.shopFormService.createShopFormGroup();

  constructor(
    protected shopService: ShopService,
    protected shopFormService: ShopFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shop }) => {
      this.shop = shop;
      if (shop) {
        this.updateForm(shop);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shop = this.shopFormService.getShop(this.editForm);
    if (shop.id !== null) {
      this.subscribeToSaveResponse(this.shopService.update(shop));
    } else {
      this.subscribeToSaveResponse(this.shopService.create(shop));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShop>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(shop: IShop): void {
    this.shop = shop;
    this.shopFormService.resetForm(this.editForm, shop);
  }
}

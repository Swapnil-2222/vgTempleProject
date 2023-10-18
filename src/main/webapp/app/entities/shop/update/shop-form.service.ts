import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IShop, NewShop } from '../shop.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IShop for edit and NewShopFormGroupInput for create.
 */
type ShopFormGroupInput = IShop | PartialWithRequiredKeyOf<NewShop>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IShop | NewShop> = Omit<T, 'date' | 'lastModified' | 'freeField7' | 'freeField8'> & {
  date?: string | null;
  lastModified?: string | null;
  freeField7?: string | null;
  freeField8?: string | null;
};

type ShopFormRawValue = FormValueOf<IShop>;

type NewShopFormRawValue = FormValueOf<NewShop>;

type ShopFormDefaults = Pick<NewShop, 'id' | 'date' | 'lastModified' | 'freeField7' | 'freeField8' | 'freeField9' | 'freeField10'>;

type ShopFormGroupContent = {
  id: FormControl<ShopFormRawValue['id'] | NewShop['id']>;
  date: FormControl<ShopFormRawValue['date']>;
  name: FormControl<ShopFormRawValue['name']>;
  description: FormControl<ShopFormRawValue['description']>;
  product: FormControl<ShopFormRawValue['product']>;
  productSize: FormControl<ShopFormRawValue['productSize']>;
  price: FormControl<ShopFormRawValue['price']>;
  emailId: FormControl<ShopFormRawValue['emailId']>;
  contactPerson: FormControl<ShopFormRawValue['contactPerson']>;
  status: FormControl<ShopFormRawValue['status']>;
  lastModified: FormControl<ShopFormRawValue['lastModified']>;
  lastModifiedBy: FormControl<ShopFormRawValue['lastModifiedBy']>;
  freeField1: FormControl<ShopFormRawValue['freeField1']>;
  freeField2: FormControl<ShopFormRawValue['freeField2']>;
  freeField5: FormControl<ShopFormRawValue['freeField5']>;
  freeField6: FormControl<ShopFormRawValue['freeField6']>;
  freeField7: FormControl<ShopFormRawValue['freeField7']>;
  freeField8: FormControl<ShopFormRawValue['freeField8']>;
  freeField9: FormControl<ShopFormRawValue['freeField9']>;
  freeField10: FormControl<ShopFormRawValue['freeField10']>;
};

export type ShopFormGroup = FormGroup<ShopFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ShopFormService {
  createShopFormGroup(shop: ShopFormGroupInput = { id: null }): ShopFormGroup {
    const shopRawValue = this.convertShopToShopRawValue({
      ...this.getFormDefaults(),
      ...shop,
    });
    return new FormGroup<ShopFormGroupContent>({
      id: new FormControl(
        { value: shopRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      date: new FormControl(shopRawValue.date),
      name: new FormControl(shopRawValue.name),
      description: new FormControl(shopRawValue.description),
      product: new FormControl(shopRawValue.product),
      productSize: new FormControl(shopRawValue.productSize),
      price: new FormControl(shopRawValue.price),
      emailId: new FormControl(shopRawValue.emailId),
      contactPerson: new FormControl(shopRawValue.contactPerson),
      status: new FormControl(shopRawValue.status),
      lastModified: new FormControl(shopRawValue.lastModified),
      lastModifiedBy: new FormControl(shopRawValue.lastModifiedBy),
      freeField1: new FormControl(shopRawValue.freeField1),
      freeField2: new FormControl(shopRawValue.freeField2),
      freeField5: new FormControl(shopRawValue.freeField5),
      freeField6: new FormControl(shopRawValue.freeField6),
      freeField7: new FormControl(shopRawValue.freeField7),
      freeField8: new FormControl(shopRawValue.freeField8),
      freeField9: new FormControl(shopRawValue.freeField9),
      freeField10: new FormControl(shopRawValue.freeField10),
    });
  }

  getShop(form: ShopFormGroup): IShop | NewShop {
    return this.convertShopRawValueToShop(form.getRawValue() as ShopFormRawValue | NewShopFormRawValue);
  }

  resetForm(form: ShopFormGroup, shop: ShopFormGroupInput): void {
    const shopRawValue = this.convertShopToShopRawValue({ ...this.getFormDefaults(), ...shop });
    form.reset(
      {
        ...shopRawValue,
        id: { value: shopRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ShopFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      date: currentTime,
      lastModified: currentTime,
      freeField7: currentTime,
      freeField8: currentTime,
      freeField9: false,
      freeField10: false,
    };
  }

  private convertShopRawValueToShop(rawShop: ShopFormRawValue | NewShopFormRawValue): IShop | NewShop {
    return {
      ...rawShop,
      date: dayjs(rawShop.date, DATE_TIME_FORMAT),
      lastModified: dayjs(rawShop.lastModified, DATE_TIME_FORMAT),
      freeField7: dayjs(rawShop.freeField7, DATE_TIME_FORMAT),
      freeField8: dayjs(rawShop.freeField8, DATE_TIME_FORMAT),
    };
  }

  private convertShopToShopRawValue(
    shop: IShop | (Partial<NewShop> & ShopFormDefaults),
  ): ShopFormRawValue | PartialWithRequiredKeyOf<NewShopFormRawValue> {
    return {
      ...shop,
      date: shop.date ? shop.date.format(DATE_TIME_FORMAT) : undefined,
      lastModified: shop.lastModified ? shop.lastModified.format(DATE_TIME_FORMAT) : undefined,
      freeField7: shop.freeField7 ? shop.freeField7.format(DATE_TIME_FORMAT) : undefined,
      freeField8: shop.freeField8 ? shop.freeField8.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}

import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../shop.test-samples';

import { ShopFormService } from './shop-form.service';

describe('Shop Form Service', () => {
  let service: ShopFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShopFormService);
  });

  describe('Service methods', () => {
    describe('createShopFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createShopFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            date: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            product: expect.any(Object),
            productSize: expect.any(Object),
            price: expect.any(Object),
            emailId: expect.any(Object),
            contactPerson: expect.any(Object),
            status: expect.any(Object),
            lastModified: expect.any(Object),
            lastModifiedBy: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
            freeField5: expect.any(Object),
            freeField6: expect.any(Object),
            freeField7: expect.any(Object),
            freeField8: expect.any(Object),
            freeField9: expect.any(Object),
            freeField10: expect.any(Object),
          }),
        );
      });

      it('passing IShop should create a new form with FormGroup', () => {
        const formGroup = service.createShopFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            date: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            product: expect.any(Object),
            productSize: expect.any(Object),
            price: expect.any(Object),
            emailId: expect.any(Object),
            contactPerson: expect.any(Object),
            status: expect.any(Object),
            lastModified: expect.any(Object),
            lastModifiedBy: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
            freeField5: expect.any(Object),
            freeField6: expect.any(Object),
            freeField7: expect.any(Object),
            freeField8: expect.any(Object),
            freeField9: expect.any(Object),
            freeField10: expect.any(Object),
          }),
        );
      });
    });

    describe('getShop', () => {
      it('should return NewShop for default Shop initial value', () => {
        const formGroup = service.createShopFormGroup(sampleWithNewData);

        const shop = service.getShop(formGroup) as any;

        expect(shop).toMatchObject(sampleWithNewData);
      });

      it('should return NewShop for empty Shop initial value', () => {
        const formGroup = service.createShopFormGroup();

        const shop = service.getShop(formGroup) as any;

        expect(shop).toMatchObject({});
      });

      it('should return IShop', () => {
        const formGroup = service.createShopFormGroup(sampleWithRequiredData);

        const shop = service.getShop(formGroup) as any;

        expect(shop).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IShop should not enable id FormControl', () => {
        const formGroup = service.createShopFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewShop should disable id FormControl', () => {
        const formGroup = service.createShopFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

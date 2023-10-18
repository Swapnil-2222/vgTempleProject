import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ShopDetailComponent } from './shop-detail.component';

describe('Shop Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShopDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ShopDetailComponent,
              resolve: { shop: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ShopDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load shop on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ShopDetailComponent);

      // THEN
      expect(instance.shop).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

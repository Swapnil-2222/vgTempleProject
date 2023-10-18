import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { DelegateDetailComponent } from './delegate-detail.component';

describe('Delegate Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DelegateDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: DelegateDetailComponent,
              resolve: { delegate: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(DelegateDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load delegate on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', DelegateDetailComponent);

      // THEN
      expect(instance.delegate).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

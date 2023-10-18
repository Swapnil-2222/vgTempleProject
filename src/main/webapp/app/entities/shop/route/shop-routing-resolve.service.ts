import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IShop } from '../shop.model';
import { ShopService } from '../service/shop.service';

export const shopResolve = (route: ActivatedRouteSnapshot): Observable<null | IShop> => {
  const id = route.params['id'];
  if (id) {
    return inject(ShopService)
      .find(id)
      .pipe(
        mergeMap((shop: HttpResponse<IShop>) => {
          if (shop.body) {
            return of(shop.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default shopResolve;

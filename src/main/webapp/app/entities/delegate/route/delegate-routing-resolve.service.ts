import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDelegate } from '../delegate.model';
import { DelegateService } from '../service/delegate.service';

export const delegateResolve = (route: ActivatedRouteSnapshot): Observable<null | IDelegate> => {
  const id = route.params['id'];
  if (id) {
    return inject(DelegateService)
      .find(id)
      .pipe(
        mergeMap((delegate: HttpResponse<IDelegate>) => {
          if (delegate.body) {
            return of(delegate.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default delegateResolve;

import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IActivity, NewActivity } from '../activity.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IActivity for edit and NewActivityFormGroupInput for create.
 */
type ActivityFormGroupInput = IActivity | PartialWithRequiredKeyOf<NewActivity>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IActivity | NewActivity> = Omit<T, 'date' | 'lastModified' | 'freeField7' | 'freeField8'> & {
  date?: string | null;
  lastModified?: string | null;
  freeField7?: string | null;
  freeField8?: string | null;
};

type ActivityFormRawValue = FormValueOf<IActivity>;

type NewActivityFormRawValue = FormValueOf<NewActivity>;

type ActivityFormDefaults = Pick<
  NewActivity,
  'id' | 'date' | 'lastModified' | 'freeField7' | 'freeField8' | 'freeField9' | 'freeField10' | 'freeField11' | 'freeField12'
>;

type ActivityFormGroupContent = {
  id: FormControl<ActivityFormRawValue['id'] | NewActivity['id']>;
  date: FormControl<ActivityFormRawValue['date']>;
  organizationName: FormControl<ActivityFormRawValue['organizationName']>;
  location: FormControl<ActivityFormRawValue['location']>;
  activityName: FormControl<ActivityFormRawValue['activityName']>;
  description: FormControl<ActivityFormRawValue['description']>;
  noOfParticipants: FormControl<ActivityFormRawValue['noOfParticipants']>;
  time: FormControl<ActivityFormRawValue['time']>;
  mobileNo: FormControl<ActivityFormRawValue['mobileNo']>;
  emailId: FormControl<ActivityFormRawValue['emailId']>;
  contactPerson: FormControl<ActivityFormRawValue['contactPerson']>;
  comments: FormControl<ActivityFormRawValue['comments']>;
  profilePhotoType: FormControl<ActivityFormRawValue['profilePhotoType']>;
  signatureImageType: FormControl<ActivityFormRawValue['signatureImageType']>;
  status: FormControl<ActivityFormRawValue['status']>;
  lastModified: FormControl<ActivityFormRawValue['lastModified']>;
  lastModifiedBy: FormControl<ActivityFormRawValue['lastModifiedBy']>;
  freeField1: FormControl<ActivityFormRawValue['freeField1']>;
  freeField2: FormControl<ActivityFormRawValue['freeField2']>;
  freeField3: FormControl<ActivityFormRawValue['freeField3']>;
  freeField4: FormControl<ActivityFormRawValue['freeField4']>;
  freeField5: FormControl<ActivityFormRawValue['freeField5']>;
  freeField6: FormControl<ActivityFormRawValue['freeField6']>;
  freeField7: FormControl<ActivityFormRawValue['freeField7']>;
  freeField8: FormControl<ActivityFormRawValue['freeField8']>;
  freeField9: FormControl<ActivityFormRawValue['freeField9']>;
  freeField10: FormControl<ActivityFormRawValue['freeField10']>;
  freeField11: FormControl<ActivityFormRawValue['freeField11']>;
  freeField12: FormControl<ActivityFormRawValue['freeField12']>;
};

export type ActivityFormGroup = FormGroup<ActivityFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ActivityFormService {
  createActivityFormGroup(activity: ActivityFormGroupInput = { id: null }): ActivityFormGroup {
    const activityRawValue = this.convertActivityToActivityRawValue({
      ...this.getFormDefaults(),
      ...activity,
    });
    return new FormGroup<ActivityFormGroupContent>({
      id: new FormControl(
        { value: activityRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      date: new FormControl(activityRawValue.date),
      organizationName: new FormControl(activityRawValue.organizationName),
      location: new FormControl(activityRawValue.location),
      activityName: new FormControl(activityRawValue.activityName),
      description: new FormControl(activityRawValue.description),
      noOfParticipants: new FormControl(activityRawValue.noOfParticipants),
      time: new FormControl(activityRawValue.time),
      mobileNo: new FormControl(activityRawValue.mobileNo),
      emailId: new FormControl(activityRawValue.emailId),
      contactPerson: new FormControl(activityRawValue.contactPerson),
      comments: new FormControl(activityRawValue.comments),
      profilePhotoType: new FormControl(activityRawValue.profilePhotoType),
      signatureImageType: new FormControl(activityRawValue.signatureImageType),
      status: new FormControl(activityRawValue.status),
      lastModified: new FormControl(activityRawValue.lastModified),
      lastModifiedBy: new FormControl(activityRawValue.lastModifiedBy),
      freeField1: new FormControl(activityRawValue.freeField1),
      freeField2: new FormControl(activityRawValue.freeField2),
      freeField3: new FormControl(activityRawValue.freeField3),
      freeField4: new FormControl(activityRawValue.freeField4),
      freeField5: new FormControl(activityRawValue.freeField5),
      freeField6: new FormControl(activityRawValue.freeField6),
      freeField7: new FormControl(activityRawValue.freeField7),
      freeField8: new FormControl(activityRawValue.freeField8),
      freeField9: new FormControl(activityRawValue.freeField9),
      freeField10: new FormControl(activityRawValue.freeField10),
      freeField11: new FormControl(activityRawValue.freeField11),
      freeField12: new FormControl(activityRawValue.freeField12),
    });
  }

  getActivity(form: ActivityFormGroup): IActivity | NewActivity {
    return this.convertActivityRawValueToActivity(form.getRawValue() as ActivityFormRawValue | NewActivityFormRawValue);
  }

  resetForm(form: ActivityFormGroup, activity: ActivityFormGroupInput): void {
    const activityRawValue = this.convertActivityToActivityRawValue({ ...this.getFormDefaults(), ...activity });
    form.reset(
      {
        ...activityRawValue,
        id: { value: activityRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ActivityFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      date: currentTime,
      lastModified: currentTime,
      freeField7: currentTime,
      freeField8: currentTime,
      freeField9: false,
      freeField10: false,
      freeField11: false,
      freeField12: false,
    };
  }

  private convertActivityRawValueToActivity(rawActivity: ActivityFormRawValue | NewActivityFormRawValue): IActivity | NewActivity {
    return {
      ...rawActivity,
      date: dayjs(rawActivity.date, DATE_TIME_FORMAT),
      lastModified: dayjs(rawActivity.lastModified, DATE_TIME_FORMAT),
      freeField7: dayjs(rawActivity.freeField7, DATE_TIME_FORMAT),
      freeField8: dayjs(rawActivity.freeField8, DATE_TIME_FORMAT),
    };
  }

  private convertActivityToActivityRawValue(
    activity: IActivity | (Partial<NewActivity> & ActivityFormDefaults),
  ): ActivityFormRawValue | PartialWithRequiredKeyOf<NewActivityFormRawValue> {
    return {
      ...activity,
      date: activity.date ? activity.date.format(DATE_TIME_FORMAT) : undefined,
      lastModified: activity.lastModified ? activity.lastModified.format(DATE_TIME_FORMAT) : undefined,
      freeField7: activity.freeField7 ? activity.freeField7.format(DATE_TIME_FORMAT) : undefined,
      freeField8: activity.freeField8 ? activity.freeField8.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}

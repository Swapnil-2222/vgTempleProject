import dayjs from 'dayjs/esm';

import { IActivity, NewActivity } from './activity.model';

export const sampleWithRequiredData: IActivity = {
  id: 19235,
};

export const sampleWithPartialData: IActivity = {
  id: 31425,
  noOfParticipants: 32455,
  contactPerson: 'lease yowza',
  lastModifiedBy: 'presuppose',
  freeField6: 4894,
  freeField8: dayjs('2023-10-18T06:44'),
  freeField9: false,
  freeField10: true,
  freeField11: false,
};

export const sampleWithFullData: IActivity = {
  id: 30627,
  date: dayjs('2023-10-18T09:28'),
  organizationName: 'parameter creepy',
  location: 'zowie query',
  activityName: 'caboose panhandle',
  description: 'given how optimistically',
  noOfParticipants: 14435,
  time: 'in',
  mobileNo: 6168,
  emailId: 'proctor humble apse',
  contactPerson: 'warped respect',
  comments: 'eventually guess',
  profilePhotoType: 'sometimes round',
  signatureImageType: 'wilted energetically credenza',
  status: 'though trampoline',
  lastModified: dayjs('2023-10-18T12:26'),
  lastModifiedBy: 'blindly concerning',
  freeField1: 'meh that kind',
  freeField2: 'across against',
  freeField3: 'for victimize',
  freeField4: 'oddly ugh',
  freeField5: 5172,
  freeField6: 16842,
  freeField7: dayjs('2023-10-18T08:30'),
  freeField8: dayjs('2023-10-17T21:42'),
  freeField9: false,
  freeField10: true,
  freeField11: false,
  freeField12: false,
};

export const sampleWithNewData: NewActivity = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

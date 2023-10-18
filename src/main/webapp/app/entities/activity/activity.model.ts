import dayjs from 'dayjs/esm';

export interface IActivity {
  id: number;
  date?: dayjs.Dayjs | null;
  organizationName?: string | null;
  location?: string | null;
  activityName?: string | null;
  description?: string | null;
  noOfParticipants?: number | null;
  time?: string | null;
  mobileNo?: number | null;
  emailId?: string | null;
  contactPerson?: string | null;
  comments?: string | null;
  profilePhotoType?: string | null;
  signatureImageType?: string | null;
  status?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: number | null;
  freeField6?: number | null;
  freeField7?: dayjs.Dayjs | null;
  freeField8?: dayjs.Dayjs | null;
  freeField9?: boolean | null;
  freeField10?: boolean | null;
  freeField11?: boolean | null;
  freeField12?: boolean | null;
}

export type NewActivity = Omit<IActivity, 'id'> & { id: null };

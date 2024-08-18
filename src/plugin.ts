import { registerPlugin } from '@capacitor/core';

import type { ZcsPlugin } from './definitions';

export const Zcs = registerPlugin<ZcsPlugin>('Zcs');

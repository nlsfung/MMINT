#!/usr/bin/env python3

import os
import sys
from jproperties import Properties


def x(path):
    pass


if __name__ == '__main__':
    var_x = 'numSignatureSites'
    var_y = 'timeOverhead'
    var_line = 'ratioPolySites'
    for operator in ['filter', 'map', 'reduce']:
        data = {}
        for signature in ['model', 'rel']:
            data[signature] = {}
            path = os.path.join(operator, signature)
            for experiment in os.listdir(path):
                exp_path = os.path.join(path, experiment)
                if not os.path.isdir(exp_path):
                    continue
                props_path = os.path.join(exp_path, 'ExperimentOut.properties')
                props = Properties()
                with open(props_path, 'rb') as props_file:
                    props.load(props_file, 'utf-8')
                    value_x, _ = props[var_x]
                    value_line, _ = props[var_line]
                    value_y_avg, _ = props[f'{var_y}.avg']
                    value_y_avg = float(value_y_avg) / 1_000_000
                    value_y_low, _ = props[f'{var_y}.lower']
                    value_y_low = float(value_y_low) / 1_000_000
                    value_y_up, _ = props[f'{var_y}.upper']
                    value_y_up = float(value_y_up) / 1_000_000
                    x = data[signature].setdefault(value_x, {})
                    x[value_line] = (value_y_avg, value_y_low, value_y_up)
        with open(f'{operator}_10types.dat', 'w') as dat_file:
            # {'model': {0: {50: (1,2,3)}}}
            out = ''
            for x in sorted(data['model'].keys()):
                if x == '0':
                    out += '0.1'
                else:
                    out += f'{x}'
                for y in sorted(data['model'][x].keys()):
                    out += ' ' + ' '.join(str(n) for n in data['model'][x][y])
                for y in sorted(data['rel'][x].keys()):
                    out += ' ' + ' '.join(str(n) for n in data['rel'][x][y])
                out += '\n'
            dat_file.write(out)


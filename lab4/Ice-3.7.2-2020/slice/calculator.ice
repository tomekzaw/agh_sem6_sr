
#ifndef CALC_ICE
#define CALC_ICE

module Demo
{
  sequence<int> seqofNumbers;
  enum operation { MIN, MAX, AVG };

  exception EmptySequenceError {}

  interface Calc
  {
    long add(int a, int b);
    long subtract(int a, int b);
    float mean(seqofNumbers nums) throws EmptySequenceError;
  };

};

#endif

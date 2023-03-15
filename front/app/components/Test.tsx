"use client";

import Container from "./Container";

export default function Test() {
  return (
    <div className='mx-auto max-w-6xl'>
      <div className='flex flex-1 items-center justify-between'>
        <div className='first flex-1 bg-red-400'>
          <ul className='flex gap-3'>
            <li>home</li>
            <li>about</li>
            <li>about</li>
            <li>about</li>
            <li>about</li>
          </ul>
        </div>
        <div className='second flex flex-1 items-center justify-center bg-orange-300'>
          <div>main logo</div>
        </div>
        <div className='third flex flex-1 bg-green-300'>
          <div className='ml-auto flex gap-3'>
            <div>login</div>
            <div>write</div>
          </div>
        </div>
      </div>
    </div>
  );
}

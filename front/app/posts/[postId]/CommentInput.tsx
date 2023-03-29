"use client";

import { useState } from "react";

export default function CommentInput() {
  const [commentInput, setCommentInput] = useState<string>("");
  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setCommentInput(event.target.value);
  };
  return (
    <div className='mt-5'>
      <div className='h-[1px] bg-primary' />
      <h2 className='p-2 text-lg font-bold text-primary'>댓글 n개</h2>
      <form method='POST' className='relative'>
        <textarea
          name='comment'
          id='comment'
          rows={3}
          className='w-full resize-none rounded-md bg-primary/5 p-3'
          placeholder='댓글을 남겨주세요'
          value={commentInput}
          onChange={handleChange}
        ></textarea>

        <p
          className={`absolute bottom-4 left-4 z-20 font-bold  ${
            commentInput.length > 100 ? "text-red-500" : "text-gray-500"
          }`}
        >
          {commentInput.length}/100
        </p>
        <button className='btn-primary btn-sm btn absolute bottom-3 right-2'>
          Send
        </button>
      </form>
    </div>
  );
}

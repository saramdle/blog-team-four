"use client";

import { useRouter } from "next/navigation";
import { useEffect, useRef, useState } from "react";
import "react-quill/dist/quill.snow.css";

export default function Write() {
  const router = useRouter();

  //hydration error
  const [mounted, setMounted] = useState(false);
  useEffect(() => {
    setMounted(true);
  }, []);

  //react quill
  const ReactQuill =
    typeof window === "object"
      ? require("react-quill")
      : () => <p>Loading ...</p>;

  const [contentsInput, setContentsInput] = useState<string>("");
  const [title, setTitle] = useState<string>("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const postingData = {
      title,
      // 로그인된 user로 대체될 예정
      author: "홍길동",
      contents: contentsInput,
      // 업로드된 사진 url로 대체될 예정
      imgUrl:
        "https://images.unsplash.com/photo-1606787366850-de6330128bfc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80",
      // db자동생성될 예정
      createdAt: new Date(),
      updatedAt: new Date(),
    };
    const response = await fetch("http://localhost:4000/posts", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(postingData),
    });
    return response.json();
  };

  const modules = {
    toolbar: [
      ["bold", "italic", "underline", "strike", "image"],
      ["blockquote"],
      [{ header: 1 }, { header: 2 }],
      [{ list: "ordered" }, { list: "bullet" }],
      [{ size: ["small", false, "large", "huge"] }],
      [{ color: [] }, { background: [] }],
      [{ align: [] }],
      ["clean"],
    ],
  };
  return (
    <div className='flex'>
      <form
        method='POST'
        className='flex flex-1 flex-col gap-5 p-4'
        onSubmit={handleSubmit}
      >
        <div className='flex items-center'>
          <input
            type='text'
            placeholder='제목을 입력하세요'
            className='text-bold w-full bg-transparent text-4xl focus:outline-none'
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <label
          htmlFor='image'
          className='w-max cursor-pointer text-gray-400 hover:text-primary'
        >
          이미지 업로드
        </label>
        <input type='file' name='image' id='image' className='hidden' />
        <div>
          {mounted && (
            <ReactQuill
              theme='snow'
              value={contentsInput}
              onChange={setContentsInput}
              className='h-[500px]'
              modules={modules}
            />
          )}
        </div>
        <div className='flex justify-between'>
          <div
            className='btn-outline btn mt-10 w-max'
            onClick={() => router.back()}
          >
            나가기
          </div>
          <button className='btn-primary btn mt-10 w-max'>발행</button>
        </div>
      </form>
      <div className='flex-1 border-l-2 p-3'>
        <h1 className='text-4xl'>{title}</h1>
        <p
          dangerouslySetInnerHTML={{ __html: contentsInput }}
          className='prose mt-4 max-w-none prose-p:m-0'
        />
      </div>
    </div>
  );
}

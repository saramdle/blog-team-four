"use client";

import Container from "@/app/components/Container";
import MainImage from "@/app/components/MainImage";
import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import { usePathname } from "next/navigation";
import CommentInput from "./CommentInput";
import Comments from "./Comments";

export default function Page() {
  const pathName = usePathname();
  const postId = pathName.split("/")[2];
  const getPost = () => axios.get(`http://localhost:4000/posts/${postId}`);
  const { isLoading, data, error } = useQuery({
    queryKey: [`posts/${postId}`],
    queryFn: getPost,
  });
  const post = data?.data;

  if (error) error;

  if (isLoading) return null;

  return (
    <>
      <MainImage
        src={post?.imgUrl}
        title={post?.title}
        createdAt={post?.createdAt}
        author={post?.author}
      />
      <Container>
        <p
          dangerouslySetInnerHTML={{ __html: post?.contents }}
          className='prose max-w-none prose-p:m-0'
        />
        <p>{}</p>
        <CommentInput postId={postId} />
        <Comments postId={postId} />
      </Container>
    </>
  );
}

// export async function generateStaticParams() {
//   const res = await fetch("http://localhost:4000/posts");
//   if (!res.ok) {
//     throw new Error("Failed to fetch all the posts");
//   }
//   const posts: Post[] = await res.json();
//   return posts.map((post) => ({
//     postId: post.id.toString(),
//   }));
// }
